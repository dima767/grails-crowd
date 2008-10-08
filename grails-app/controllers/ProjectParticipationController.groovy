import grailscrowd.core.*

class ProjectParticipationController extends SecureController {

    def allowedMethods = [invite: 'POST', acceptParticipationInvitation: 'POST',
            rejectParticipationInvitation: 'POST', requestParticipation: 'POST',
            approveParticipationRequest: 'POST', rejectParticipationRequest: 'POST', index: 'POST']

    def beforeInterceptor = [action: this.&auth]

    def index = {}

    def invite = {
        def invitee = Member.get(params['invitee.id'])

        long projectId =  !params['project.id'] || !(params['project.id'].isLong())?-1L:Long.valueOf(params['project.id'])
        if (projectId<1) {
            redirect(controller: 'member', action: 'viewProfile', params: [_name: invitee.name, ])
            return
        }
        def project = GrailsProject.get(projectId)
        def projectCreator = freshCurrentlyLoggedInMember()
        project.inviteParticipant(projectCreator, invitee)
		flash.messageClass = 'info-box'
        flash.message = """Project participation invitation has been submitted.
                           A reply from the member you are inviting should appear in your inbox."""
        render(view: '/member/viewProfile', model: [member: invitee])
		
		if(invitee.canBeNotifiedViaEmail) {
			try {
				sendMail {
		   			to invitee.email
		   			subject "[Grails Crowd] project participation invitation"
		   			body "Grails Crowd member '$projectCreator.displayName' wants you to join project '$project.name'\n\nGo to your mailbox to see more details: ${createLink(controller: 'mailbox', action: 'index', absolute: true)}"
				}
			}
			catch (Exception e) {
				log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
			}
				
		}
    }

    def acceptParticipationInvitation = {
        withProject {projectMap ->
            projectMap.project.acknowlegeParticipationAcceptance(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
			
			if(projectMap.creator.canBeNotifiedViaEmail) {
				try {
					sendMail {
						to projectMap.creator.email
						subject "[Grails Crowd] project participation acceptance"
						body "Grails Crowd member '$projectMap.inviteeOrRequestor.displayName' has joined project '$projectMap.project.name'\n\nGo to your mailbox to see more details: ${createLink(controller: 'mailbox', action: 'index', absolute: true)}"
					}
				}
				catch (Exception e) {
					log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
				}
			}
        }
    }

    def rejectParticipationInvitation = {
        withProject {projectMap ->
            projectMap.project.rejectParticipationInvitation(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
        	
			if(projectMap.creator.canBeNotifiedViaEmail) {
				try {
					sendMail {
						to projectMap.creator.email
						subject "[Grails Crowd] project participation rejection"
						body "Grails Crowd member '$projectMap.inviteeOrRequestor.displayName' has rejected to join project '$projectMap.project.name'\n\nGo to your mailbox to see more details: ${createLink(controller: 'mailbox', action: 'index', absolute: true)}"
					}
				}
				catch (Exception e) {
					log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
				}
			}		
		}
    }

    def requestParticipation = {
        def requestor = Member.get(params['requestor.id'])
        if (!params['project.id']) {
            redirect(controller: 'member', action: 'viewProfile', params: [_name: requestor.name])
        }
        def project = GrailsProject.get(params['project.id'])
        project.requestParticipation(requestor)
        flash.messageClass = 'info-box'
        flash.message = """Project participation request has been submitted.
                           A reply from the project creator should appear in your inbox."""
        render(view: '/grailsProject/viewProject', model: [grailsProject: project])
		
		if(project.creator.canBeNotifiedViaEmail) {
			try {
				sendMail {
					to project.creator.email
					subject "[Grails Crowd] project participation request"
					body "Grails Crowd member '$requestor.displayName' has requested to join project '$project.name' as a participant.\n\nGo to your mailbox to see more details: ${createLink(controller: 'mailbox', action: 'index', absolute: true)}"
				}
			}
			catch (Exception e) {
				log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
			}
		}

    }

    def approveParticipationRequest = {
        withProject {projectMap ->
            projectMap.project.approveRequestedParticipation(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
		
			if(projectMap.inviteeOrRequestor.canBeNotifiedViaEmail) {
				try {
					sendMail {
						to projectMap.inviteeOrRequestor.email
						subject "[Grails Crowd] project participation approval"
						body "Grails Crowd member '$projectMap.creator.displayName' has approved your request to join project '$projectMap.project.name' as a participant.\n\nGo to your mailbox to see more details: ${createLink(controller: 'mailbox', action: 'index', absolute: true)}"
					}
				}
				catch (Exception e) {
					log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
				}
			}
        }
    }

    def rejectParticipationRequest = {
        withProject {projectMap ->
            projectMap.project.rejectRequestedParticipation(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
			
			if(projectMap.inviteeOrRequestor.canBeNotifiedViaEmail) {
				try {
					sendMail {
						to projectMap.inviteeOrRequestor.email
						subject "[Grails Crowd] project participation disapproval"
						body "Grails Crowd member '$projectMap.creator.displayName' has rejected your request to join project '$projectMap.project.name' as a participant.\n\nGo to your mailbox to see more details: ${createLink(controller: 'mailbox', action: 'index', absolute: true)}"
					}
				}
				catch (Exception e) {
					log.debug("Exception is caught during send mail [${e.getMessage()}] Continueing...")
				}
			}
        }
    }

    private def withProject(callable) {
        def inviteeOrRequestor = Member.findByName(params.inviteeOrRequestor)
        def creator = Member.findByName(params.creator)
        def project = GrailsProject.get(params.projectId)
        callable([project: project, creator: creator, inviteeOrRequestor: inviteeOrRequestor])
        redirect(controller: 'mailbox')
    }
}
