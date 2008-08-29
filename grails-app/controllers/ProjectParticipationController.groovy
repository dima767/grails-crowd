import grailscrowd.core.*

class ProjectParticipationController extends SecureController {

    def allowedMethods = [invite: 'POST', acceptParticipationInvitation: 'POST',
            rejectParticipationInvitation: 'POST', requestParticipation: 'POST',
            approveParticipationRequest: 'POST', rejectParticipationRequest: 'POST', index: 'POST']

    def beforeInterceptor = [action: this.&auth]

    def index = {}

    def invite = {
        def invitee = Member.get(params['invitee.id'])
        if (params['project.id'] == 'null') {
            redirect(controller: 'member', action: 'viewProfile', params: [_name: invitee.name])
        }
        def project = GrailsProject.get(params['project.id'])
        def projectCreator = freshCurrentlyLoggedInMember()
        project.inviteParticipant(projectCreator, invitee)
		flash.messageClass = 'info-box'
        flash.message = """Project participation invitation has been submitted.
                           A reply from the member you are inviting should appear in your inbox."""
        render(view: '/member/viewProfile', model: [member: invitee])
    }

    def acceptParticipationInvitation = {
        withProject {projectMap ->
            projectMap.project.acknowlegeParticipationAcceptance(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
        }
    }

    def rejectParticipationInvitation = {
        withProject {projectMap ->
            projectMap.project.rejectParticipationInvitation(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
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
    }

    def approveParticipationRequest = {
        withProject {projectMap ->
            projectMap.project.approveRequestedParticipation(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
        }
    }

    def rejectParticipationRequest = {
        withProject {projectMap ->
            projectMap.project.rejectRequestedParticipation(projectMap.creator,
                    projectMap.inviteeOrRequestor, params.messageId.toLong())
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
