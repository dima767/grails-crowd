import grailscrowd.core.*

class GrailsProjectController extends SecureController {

    ProjectService projectService

    def beforeInterceptor = [action: this.&auth, only: ['editProject',
            'handleProjectCreation',
            'updateProject',
            'projectCreationForm',
            'postComment']]

    static allowedMethods = [projectCreationForm: 'GET',
            handleProjectCreation: 'POST',
            editProject: 'GET',
            updateProject: 'POST',
            viewProject: 'GET',
            postComment: 'POST']

    def projectCreationForm = {
        [project: new GrailsProject()]
    }

    def findByTeamCount = {
        //where size(gp.participants) >= ${params.min.toInteger()} and size(gp.participants) <= ${params.max.toInteger()}        

        def grailsProjectList = GrailsProject.findAll("from GrailsProject")
        render(view: 'list', model: [grailsProjectList: grailsProjectList])
    }

    def findRandom = {
        def grailsProject = null
        withGrailsProjectIds {grailsProjectIds ->
            grailsProject = GrailsProject.get(grailsProjectIds[-1])
            //Remove the last one
            grailsProjectIds.pop()
        }

        if (grailsProject) {
            render(view: 'discover', model: [project: grailsProject])
        }
        else {
            redirect(uri: '/')
        }
    }

    def findLastSevenDays = {
        def grailsProjects = GrailsProject.findAllByDateCreatedBetween(new Date() - 7, new Date())
        render(view: 'last7Days', model: [grailsProjects: grailsProjects])
    }

    def listProjectLocations = {
        def total = GrailsProject.countByPrimaryLocationIsNotNull()
        def locations = GrailsProject.executeQuery("select distinct p.primaryLocation \
                                                from grailscrowd.core.GrailsProject p \
                                                where p.primaryLocation is not null \
                                                order by p.primaryLocation", [:], params)

        render(view: '/shared/locationList', model: [locations: locations,
                locationListHeader: 'Discover projects by location',
                paginatingController: controllerName, paginatingAction: actionName, targetController: controllerName, total: total])
    }

    def findByName = {
        def total = GrailsProject.count()
        def grailsProjects = GrailsProject.listOrderByName(params)
        render(view: 'list', model: [projects: grailsProjects, navMenu: 'discoverNavigationByName',
                paginatingController: controllerName, paginatingAction: actionName, total: total])
    }

    def findByLocation = {
        def total = GrailsProject.countByPrimaryLocation(params.q)
        def grailsProjects = GrailsProject.findAllByPrimaryLocation(params.q, params)
        render(view: 'list', model: [projects: grailsProjects, navMenu: 'discoverNavigationByLocation',
                paginatingController: controllerName, paginatingAction: actionName, total: total,
                menuContext: [location: params.q]])
    }

    def findByTagGlobally = {
        def total = 0
        def projects = []
        withTag {tag ->
            if (tag) {
                total = Tagging.countByTag(tag)
                //Using CrieteriaBuilder here, so we could query by 'taggings' association
                projects = GrailsProject.createCriteria().list(params) {
                    taggings {
                        eq('tag', tag)
                    }
                } //Criteria builder
                //Sort by name since GrailsProject implements Comparable and the name property is its natural order
                projects.sort()

            } //if
        } //Closure
        render(view: 'list', model: [projects: projects, navMenu: 'discoverNavigationByTag',
                paginatingController: controllerName, paginatingAction: actionName, total: total,
                menuContext: [tag: params.selectedTag]])
    }

    //Thin wrapper around 'findByTagForMember' action
    //Work around richUI tag cloud taglib limitation of not passing params
    //to controllers
    def findByTagForMemberWrapper = {        
        redirect(action: 'findByTagForMember',
                params: [_name: session.cloudOwningMemberName, selectedTag: params.selectedTag])
    }

    def findByTagForMember = {
        def total = 0
        def projects = []        
        def member = Member.findByName(params._name)        
        withTag {tag ->
            if (tag) {
                if (!member) {
                    redirect(uri: '/notAllowed')
                }
                total = Tagging.countByTagAndMember(tag, member)
                //Using CrieteriaBuilder here, so we could query by 'taggings' association
                projects = GrailsProject.createCriteria().list(params) {
                    taggings {
                        eq('tag', tag)
                        eq('member', member)
                    }
                } //Criteria builder
                //Sort by name since GrailsProject implements Comparable and the name property is its natural order
                projects.sort()
            } //if
        } //Closure
        render(view: 'list', model: [projects: projects, navMenu: 'memberProjectsNavigationByTag',
                paginatingController: controllerName, paginatingAction: actionName, total: total,
                menuContext: [tag: params.selectedTag, member: member]])
    }

    def handleProjectCreation = {
        params.tagTokens = params?.tags.encodeAsUniqueList()
        def project = projectService.createProject(params, freshCurrentlyLoggedInMember())
        if (project.hasErrors()) {
            render(view: 'projectCreationForm', model: [project: project])
            return
        }
        redirect(action: "viewProject", id: project.id)
    }

    def editProject = {
        return [project: GrailsProject.get(params.id)]
    }

    def updateProject = {
        def project = GrailsProject.get(params.id)
        projectService.updateProject(project, params, freshCurrentlyLoggedInMember())
        flash.messageClass = 'info-box'
        flash.message = "Project has been updated successfuly."
        render(view: 'editProject', model: [project: project])
    }

    def viewProject = {
        def projectId = null
        try {
            projectId = params.id.toLong()
        }
        catch (NumberFormatException e) {
            render(view: '404')
            return
        }
        def grailsProject = GrailsProject.get(projectId)
        if (!grailsProject) {
            render(view: '404')
            return
        }
		//TODO: refactor into filter?
        if (!loggedIn() || !(grailsProject.isCreator(session.memberId))) {
            grailsProject.recordOnePublicViewIfEligible(request.remoteAddr)
        }
        render(view: 'viewProject', model: [grailsProject: grailsProject])
    }

    def postComment = {
        def grailsProject = GrailsProject.get(params.id)
        if (grailsProject) {
            Comment comment = new Comment(params)
            comment.project = grailsProject
            comment.member = freshCurrentlyLoggedInMember()
            grailsProject.addToComments(comment)
			//Only send email notification if posting a comment succeeded
			if(grailsProject.save()) {	
				def recipients = grailsProject.uniqueMembersWhoPostedComments.findAll { it.canBeNotifiedViaEmail }.collect { it.email }
				recipients += grailsProject.creator.email
				recipients -= comment.member.email
	
				if(recipients) {
					try {
						sendMail {     
							//Mails plugin BUG - currently bcc, cc doesn't work. Won't send blind emails for now. Hopefuly later versions of Mail plugin will address that.
							bcc recipients.toArray()
			   				subject "A new comment for project [${grailsProject.name}] has been posted"     
			   				body "${comment.member.displayName} said:\n\n${comment.body}\n\nSee the comment in context: ${createLink(controller: 'grailsProject', action: 'viewProject', id: params.id, absolute: true)}"
						}
					}
					catch (Exception e) {
						log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
					}
				}
			}
			redirect(action: 'viewProject', id: grailsProject.id)
			return 
        }
        redirect(uri: '/notAllowed')
    }

    def commentsFeed = {
        def grailsProject = GrailsProject.get(params.id)

        render(feedType: "atom") {
            title = "Grails Crowd project (${grailsProject.name}): comments"
            description = "Comments for ${grailsProject.name} project"
            link = "http://grailscrowd.com/projects/${grailsProject.id}/comments/feed/atom"

            grailsProject.comments.each() {comment ->
                entry {
					title = "New comment from Grails Crowd member ${comment.member.displayName} made on ${g.niceDate(date:comment.dateCreated)}"                   
					link = "http://grailscrowd.com/projects/${grailsProject.id}"
                    author = comment.member.displayName
                    publishedDate = comment.dateCreated
					content {
                        comment.body
                    }
                }
            }
        }
    }

	/**Atom feed of the latest projects */
	def latestFeed = {
        def latestProjects = GrailsProject.findAllByDateCreatedBetween(new Date() - 1, new Date(), [max:5, sort:"dateCreated", order:"desc"])

        render(feedType: "atom") {
            title = "Grails Crowd latest projects"
            description = "Latest projects registered with Grails Crowd"
            link = "http://grailscrowd.com/projects/latest/feed/atom"

            latestProjects.each() {project ->
                entry {
					title = "Grails project ${project.name} has been registered on ${g.niceDate(date:project.dateCreated)}"                   
					link = "http://grailscrowd.com/projects/${project.id}"
                    author = project.creator.displayName
                    publishedDate = project.dateCreated
					content {
                        project.description
                    }
                }
            }
        }
    }

    private def withTag(callable) {
        if (!params.selectedTag) {
            redirect(uri: '/notAllowed')
            return
        }
        def tag = Tag.findByName(params.selectedTag)
        callable(tag)
    }

    private def withGrailsProjectIds(callable) {
        def grailsProjectIds = session.grailsProjectIds
        if (!grailsProjectIds) {
            grailsProjectIds = GrailsProject.withCriteria {
                projections {
                    property('id')
                }
            }
            if (!grailsProjectIds.isEmpty()) {
                //Some projects are in the database:
                //Shuffle 'em up
                Collections.shuffle(grailsProjectIds)
                session.grailsProjectIds = grailsProjectIds
            }
            else {
                //No projects are in the database
                return
            }
        }
        callable(grailsProjectIds)
        if (grailsProjectIds.isEmpty()) {
            session.grailsProjectIds == null
        }
    }
}
