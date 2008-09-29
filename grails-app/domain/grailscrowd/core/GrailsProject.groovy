package grailscrowd.core

import grailscrowd.capability.NumberOfViewsTrackable

class GrailsProject extends NumberOfViewsTrackable implements Comparable {

    String uri
    String name
    String description
    String primaryLocation
    String architectureDescription

    // auto-timestamping
    Date dateCreated
    Date lastUpdated

    //The creator or owner of this project in grailscrowd 
    Long creatorMemberId = -1L

    static hasMany = [participants: ProjectParticipation, taggings: Tagging, comments: Comment, favoriteReferences:FavoriteProjectReference]
	SortedSet taggings
	SortedSet comments

    static constraints = {
        uri(blank: false, unique: true, url: true)
        name(blank: false, maxSize: 100, unique: true)
        description(blank: false, maxSize: 4000)
        primaryLocation(nullable: true, maxSize: 2000)
        architectureDescription(nullable: true, maxSize: 4000)
    }
    //static fetchMode = [participants: 'eager']

    def requestParticipation(Member requestor) {
        if (requestor.id == this.creatorMemberId) {
            throw new IllegalArgumentException("The member is the creator of this project. Cannot request participation for the owning project")
        }
        if (participatesInThisProject(requestor)) {
            throw new IllegalArgumentException("The member already participates in this project or has a pending participation request. Cannot request participation in projects more than once")
        }
        if (!requestor.isAwareOf(this)) {
            Message.withTransaction {txStatus ->
                Message.newRequestMessageFor(ProjectParticipation.request(requestor, this))
            }
        }
    }

    def inviteParticipant(creator, invitee) {
        enforceParticipationInvariants(creator, invitee)
        if (!invitee.isAwareOf(this)) {
            Message.withTransaction {txStatus ->
                Message.newInvitationMessageFor(ProjectParticipation.pending(invitee, this))
            }
        }
    }

    def approveRequestedParticipation(creator, requestor, messageId) {
        withParticipationInvitationOrRequest(creator, requestor, messageId) {requestedParticipation ->
            Message.newRequestApprovalMessageFor(requestedParticipation.accept())
        }
    }

    def rejectRequestedParticipation(creator, requestor, messageId) {
        withParticipationInvitationOrRequest(creator, requestor, messageId) {requestedParticipation ->
            Message.newRequestRejectionMessageFor(requestedParticipation.reject())
        }                
    }

    def acknowlegeParticipationAcceptance(creator, invitee, messageId) {
        withParticipationInvitationOrRequest(creator, invitee, messageId) {invitation ->
            Message.newInvitationAcceptanceMessageFor(invitation.accept())
        }
    }

    def rejectParticipationInvitation(creator, invitee, messageId) {
        withParticipationInvitationOrRequest(creator, invitee, messageId) {invitation ->
            Message.newInvitationRejectionMessageFor(invitation.reject())
        }
    }

    void setCreator(Member owner) {
        if (hasACreator() && this.creatorMemberId != owner.id) {
            throw new IllegalArgumentException("Cannot change the owner of this project.")
        }
        this.creatorMemberId = owner.id
    }

    def getCreator() {
        if (!hasACreator()) {
            throw new IllegalStateException("Cannot call this method until this project has been fully created.")
        }
        def creator = this.participants.find {it.participant.id == this.creatorMemberId}
        if (!creator) {
            throw new IllegalStateException("Cannot call this method until this project has been fully created.")
        }
        return creator.participant
    }

    def allActiveParticipants() {
        this.participants.findAll {(it.participant.id != this.creatorMemberId) &&
                                    (it.isActive())}.participant
    }

    def relationshipRoleFor(member) {
        if(participatesInThisProject(member)) {
            return isCreator(member.id) ? 'I am the creator of' : 'I participate in'
        }
        else {
            return 'None'
        }
    }

    def isCreator(memberId) {
        getCreator().id == memberId
    }

    def getCommaSeparatedTags() {
        this.taggings?.tag?.name?.join(',')
    }
    
    def hasAnyMiscInfo() {
        (this.uri != null) || (this.primaryLocation != null)
    }

	def getUniqueMembersWhoPostedComments() {
		this.comments.member.unique()
	}

    def hasAnyActiveParticipants() {
        allActiveParticipants().size() > 0
    }

    def hasAnyTags() {
        this.taggings.size() > 0
    }

    // projects are sorted alphabetically, by default
    public int compareTo(Object obj) {
        return name <=> obj.name
    }

    private def withParticipationInvitationOrRequest(creator, inviteeOrRequestor, messageId, callable) {
        enforceParticipationInvariants(creator, inviteeOrRequestor)
        def par = findParticipantionFor(inviteeOrRequestor, ProjectParticipation.PENDING)
        if (!par) {
            par = findParticipantionFor(inviteeOrRequestor, ProjectParticipation.REQUESTED)
            if(!par) {
                throw new IllegalStateException('The invitation or request does not exist.')
            }
        }
        Message.withTransaction {txStatus ->
            if(par.isPending()) {
                inviteeOrRequestor.mailbox.markMessageAsAcknowleged(messageId)
            }
            else if(par.isRequested()) {
                creator.mailbox.markMessageAsAcknowleged(messageId)                
            }
            callable(par)
            save()
        }
    }

    private def enforceParticipationInvariants(creator, requestor) {
        if (creator?.id != this.creatorMemberId) {
            throw new IllegalArgumentException('Wrong project creator')
        }
        if (creator?.id == requestor?.id) {
            throw new IllegalArgumentException('Cannot act for yourself')
        }
        if (participatesInThisProject(requestor)) {
            throw new IllegalArgumentException("The member already participates in this project.")
        }
    }

    private def participatesInThisProject(member) {
        null != findParticipantionFor(member, ProjectParticipation.ACTIVE)
    }

    private def findParticipantionFor(member, participationStatus) {
        this.participants.find {(it.participant.id == member.id && it.status == participationStatus)}
    }

    private def hasACreator() {
        -1L != this.creatorMemberId
    }
}
