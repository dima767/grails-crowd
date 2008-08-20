package grailscrowd.core

class ProjectParticipation {
    // AJAX auto-suggest, so that people reuse the same role names
    //String role

    byte status
    Date dateCreated

    static belongsTo = [participant: Member, project: GrailsProject]

    static REQUESTED = 1
    static PENDING = 2
    static ACTIVE = 3
    static REJECTED = -1

    /*static constraints = {
        status(inList: [REQUESTED, PENDING, ACTIVE, REJECTED])
    }*/

    static def ProjectParticipation request(member, project) {
        doCreate(member, project, REQUESTED)
    }

    static def ProjectParticipation active(member, project) {
        def par = doCreate(member, project, ACTIVE)
    }

    static def ProjectParticipation pending(member, project) {
        doCreate(member, project, PENDING)
    }

    private static def doCreate(member, project, status) {
        def par = ProjectParticipation.findByParticipantAndProject(member, project)
        if (!par) {
            par = new ProjectParticipation(status: status)
            member?.addToProjectParticipations(par)
            project?.addToParticipants(par)
            par.save()
        }
        return par
    }

    def reject() {
        if(this.status == REQUESTED || this.status == PENDING) {
            this.status = REJECTED
        }
        return this
    }

    def accept() {
        if (this.status == PENDING || this.status == REQUESTED) {
            this.status = ACTIVE
        }
        return this
    }

    def isActive() {
        this.status == ACTIVE
    }

    def isPending() {
        this.status == PENDING
    }

    def isRequested() {
        this.status == REQUESTED
    }
} 
