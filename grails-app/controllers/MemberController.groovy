import grailscrowd.core.*

class MemberController extends SecureController {

    def allowedMethods = [registrationForm: 'GET', handleRegistration: 'POST',
            findRandom: 'GET',
            editProfile: 'GET', saveProfile: 'POST', updateProfile: 'POST', viewProfile: 'GET',
			listMemberLocations: 'GET', latestFeed: 'GET']

    def beforeInterceptor = [action: this.&auth, only: ['editProfile', 'saveProfile', 'updateProfile']]

    //Just renders the "registrationForm" view
    def registrationForm = {}

    def handleRegistration = {
        def member = new Member(params)
        member.mailbox = new Mailbox()
        if (params.password != params.confirm) {
            onUpdateAttempt 'Passwords you entered do not match. Please try again.', false
            render(view: 'registrationForm', model: [member: member])
            return
        }
        if (member.save()) {
            session.memberId = member.id
            redirect(action: editProfile)
        }
        else {
            render(view: 'registrationForm', model: [member: member])
        }
    }

    def editProfile = {
        [member: freshCurrentlyLoggedInMember()]
    }

    def updateProfile = {
        def member = freshCurrentlyLoggedInMember()
        member.properties = params
        if (member.save()) {
            onUpdateAttempt 'Your profile has been updated successfuly.', true
        }
        render(view: 'editProfile', model: [member: member])
    }

    def findRandom = {
        def member = null
        withMemberIds {memberIds ->
            member = Member.get(memberIds[-1])
            //Remove the last one
            memberIds.pop()
        }

        if (member) {
            render(view: 'discover', model: [member: member])
        }
        else {
            redirect(uri: '/')
        }
    }

    def listMemberLocations = {
        def total = Member.countByLocationIsNotNull()
        def locations = Member.executeQuery("select distinct m.location \
                                                from grailscrowd.core.Member m \
                                                where m.location is not null \
                                                order by m.location", [:], params)

        render(view: '/shared/locationList', model: [locations: locations,
                locationListHeader: 'Discover members by location',
                paginatingController: controllerName, paginatingAction: actionName, total: total])
    }

    def findByName = {
        def total = Member.count()
        def members = Member.listOrderByDisplayName(params)
        render(view: 'list', model: [members: members, navMenu: 'discoverNavigationByName',
                paginatingController: controllerName, paginatingAction: actionName, total: total])
    }

    def findByLocation = {
        def total = Member.countByLocation(params.q)
        def members = Member.findAllByLocation(params.q, params)
        render(view: 'list', model: [members: members, navMenu: 'discoverNavigationByLocation',
                paginatingController: controllerName, paginatingAction: actionName, total: total,
                menuContext: [location: params.q]])
    }

    def viewProfile = {
        def member = Member.findByName(params._name)
        if (!member) {
            render(view: '404')
            return
        }
        //TODO: refactor into filter?
        if (!loggedIn() || session.memberId != member.id) {
            member.recordOnePublicViewIfEligible(request.remoteAddr)
        }
        //session.member?.refresh()        
        [member: member]
    }

    def createdProjects = {
        def grailsProjects = freshCurrentlyLoggedInMember().createdProjects
        render(view: '/grailsProject/list', model: [projects: grailsProjects, navMenu: '/shared/navHeader',
                menuContext: [header: 'Projects you created']])
    }

    def participatingInProjects = {
        def grailsProjects = freshCurrentlyLoggedInMember().participatingInProjects
        render(view: '/grailsProject/list', model: [projects: grailsProjects, navMenu: '/shared/navHeader',
                menuContext: [header: 'Projects you participate in']])
    }

    def colleagues = {
        def members = freshCurrentlyLoggedInMember().projectColleagues
        render(view: '/member/list', model: [members: members, navMenu: '/shared/navHeader',
                menuContext: [header: 'Your project colleagues']])
    }

	/**Atom feed of the latest members */
	def latestFeed = {
        def latestMembers = Member.findAllByJoinedOnBetween(new Date() - 1, new Date(), [max:5, sort:"joinedOn", order:"desc"])

        render(feedType: "atom") {
            title = "Grails Crowd latest members"
            description = "Latest members registered with Grails Crowd"
            link = g.createLink(controller: "member", action: "latestFeed", absolute: true)

            latestMembers.each() {member ->
                entry {
					title = "Member ${member.displayName} has registered on ${g.niceDate(date:member.joinedOn)}"                   
					link = g.createLink(controller: "member", action: "viewProfile", params: [_name:member.name], absolute: true)
                    author = member.displayName
                    publishedDate = member.joinedOn
					content {
                        member.about
                    }
                }
            }
        }
    }

    private def withMemberIds(callable) {
        def memberIds = session.memberIds
        if (!memberIds) {
            memberIds = Member.withCriteria {
                projections {
                    property('id')
                }
            }
            if (!memberIds.isEmpty()) {
                //Some members are in the database:
                //Shuffle 'em up
                Collections.shuffle(memberIds)
                session.memberIds = memberIds
            }
            else {
                //No members are in the database
                return
            }
        }
        callable(memberIds)
        if (memberIds.isEmpty()) {
            session.memberIds == null
        }
    }

}
