package grailscrowd.core

/*TODO: Refactor into AbstractMessage and more specialized kind of messages e.g. ProjectParticipationMessage, etc. */
class Message {

    String subject
    String body
    Date sentDate = new Date()
    Long projectParticipationId
    String fromMember
    String projectName
    Long projectId
    String status
    byte kind

    static NEW = 'new'
    static SEEN = 'seen'
    static ACKNOWLEGED = 'ack'
    static ARCHIVED = 'archived'
    static PROJECT_INVITATION = 1
    static PROJECT_JOIN_REQUEST = 2
    static PROJECT_REQUEST_APPROVAL = 3
    static PROJECT_REQUEST_DISAPPROVAL = 4
    static PROJECT_INVITATION_REJECTION = 5
    static PROJECT_INVITATION_ACCEPTANCE = 6
    static OTHER = 0

    static belongsTo = [mailbox: Mailbox]

    static constraints = {
        subject(blank: false, maxSize: 1000)
        body(blank: false, maxSize: 4000)
        projectParticipationId(nullable: true)
        fromMember(blank: false, maxSize: 50)
    }

    static def newInvitationMessageFor(ProjectParticipation invitation) {
        def msgBody = """Grails Crowd member <b>${invitation.project.creator.displayName}</b>
                         has invited you to join project <b>${invitation.project.name}</b>
                         as a participant, because he/she thinks you might be involved in the development of that project."""


        withMessage(subject: "Invitation to join a project",
                body: msgBody,
                participationId: invitation.id,
                fromMember: invitation.project.getCreator().name,
                status: NEW,
                kind: PROJECT_INVITATION) {message ->
            message.projectName = invitation.project.name
            message.projectId = invitation.project.id
            invitation.participant.mailbox.addToMessages(message)
        }
    }

    static def newInvitationAcceptanceMessageFor(ProjectParticipation invitation) {
        def msgBody = """Grails Crowd member <b>${invitation.participant.displayName}</b>
                         has accepted your invitation to join project <b>${invitation.project.name}</b>
                         as a participant. He/she is now your colleague!"""


        withMessage(subject: "Grails Crowd member has joined your project",
                body: msgBody,
                participationId: invitation.id,
                fromMember: invitation.participant.name,
                status: NEW,
                kind: PROJECT_INVITATION_ACCEPTANCE) {message ->
            message.projectName = invitation.project.name
            message.projectId = invitation.project.id
            invitation.project.getCreator().mailbox.addToMessages(message)
        }
    }

    static def newInvitationRejectionMessageFor(ProjectParticipation invitation) {
        def msgBody = """Grails Crowd member <b>${invitation.participant.displayName}</b>
                         has rejected your invitation to join project <b>${invitation.project.name}</b>
                         as a participant."""


        withMessage(subject: "Grails Crowd member has rejected your invitation",
                body: msgBody,
                participationId: invitation.id,
                fromMember: invitation.participant.name,
                status: NEW,
                kind: PROJECT_INVITATION_REJECTION) {message ->
            message.projectName = invitation.project.name
            message.projectId = invitation.project.id
            invitation.project.getCreator().mailbox.addToMessages(message)
        }
    }

    static def newRequestMessageFor(ProjectParticipation request) {
       def msgBody = """Grails Crowd member <b>${request.participant.displayName}</b>
                         has requested to join project <b>${request.project.name}</b>
                         as a participant, because he/she might be involved in the development of that project."""

        withMessage(subject: "Project [${request.project.name}] participation request",
                body: msgBody,
                participationId: request.id,
                fromMember: request.participant.name,
                status: NEW,
                kind: PROJECT_JOIN_REQUEST) {message ->
            message.projectName = request.project.name
            message.projectId = request.project.id
            request.project.getCreator().mailbox.addToMessages(message)
        }
    }

    static def newRequestApprovalMessageFor(ProjectParticipation approval) {
        def msgBody = """Grails Crowd member <b>${approval.project.creator.displayName}</b>
                         has approved your request to join project <b>${approval.project.name}</b>
                         as a participant. He/she is now your colleague!"""

        withMessage(subject: "Project [${approval.project.name}] participation approval",
                body: msgBody,
                participationId: approval.id,
                fromMember: approval.project.creator.name,
                status: NEW,
                kind: PROJECT_REQUEST_APPROVAL) {message ->
            message.projectName = approval.project.name
            message.projectId = approval.project.id
            approval.participant.mailbox.addToMessages(message)
        }
    }

    static def newRequestRejectionMessageFor(ProjectParticipation rejection) {
        def msgBody = """Grails Crowd member <b>${rejection.project.creator.displayName}</b>
                         has rejected your request to join project <b>${rejection.project.name}</b>
                         as a participant."""

        withMessage(subject: "Project [${rejection.project.name}] participation rejection",
                body: msgBody,                
                participationId: rejection.id,
                fromMember: rejection.project.creator.name,
                status: NEW,
                kind: PROJECT_REQUEST_DISAPPROVAL) {message ->
            message.projectName = rejection.project.name
            message.projectId = rejection.project.id
            rejection.participant.mailbox.addToMessages(message)
        }
    }

    def markAsSeen() {
        this.status = SEEN
    }

    def markAsAcknowleged() {
        this.status = ACKNOWLEGED
    }

    def markAsArchived() {
        this.status = ARCHIVED
    }

    def isNew() {
        this.status == NEW
    }

    def isAcknowleged() {
        this.status == ACKNOWLEGED
    }

    def isArchived() {
        this.status == ARCHIVED
    }

    def isProjectParticipationInvitation() {
        this.kind == PROJECT_INVITATION
    }

    def isProjectParticipationRequest() {
        this.kind == PROJECT_JOIN_REQUEST
    }

    def isProjectParticipationAcknowlegement() {
        this.kind == PROJECT_INVITATION_ACCEPTANCE || this.kind == PROJECT_INVITATION_REJECTION ||
                this.kind == PROJECT_REQUEST_APPROVAL || this.kind == PROJECT_REQUEST_DISAPPROVAL
    }

	def isDirect() {
		this.kind == OTHER
	}

    private static withMessage(Map args, Closure callable) {
        def message = new Message(subject: args.subject)
        message.body = args.body
        message.projectParticipationId = args.participationId
        message.fromMember = args.fromMember
        message.status = args.status
        message.kind = args.kind
        callable(message)
    }
}
