package grailscrowd.core
/** added a test line to determine svn working status (mgk) **/
//The idea of mailbox is kind of like del.icio.us for:username idea.
//The mailbox could be used to communicate invitations and requests approvals to join a project, etc.
//Another way would be to use regular email, but that would be the "more intrusive" option, IMO 

class Mailbox {
    static hasMany = [messages: Message]
    static belongsTo = [member: Member]
    static fetchMode = [messages: 'eager']


    def hasAnyDisplayableMessages() {
        getVisibleMessages().size() > 0
    }

    def hasAnyNewMessages() {
        this.messages.any { it.isNew() }
    }

    def getNumberOfNewMessages() {
        this.messages.findAll { it.isNew() }.size()
    }

    def getVisibleMessages() {
        this.messages.findAll {!(it.isAcknowleged() || it.isArchived()) }
    }

    def getMessage(id) {
        this.messages.find {it.id == id}
    }

    def markMessageAsSeen(id) {
        def msg = getMessage(id)
        msg?.markAsSeen()
        return msg
    }

    def markMessageAsArchived(id) {
        def msg = getMessage(id)
        msg?.markAsArchived()       
    }

    def markMessageAsAcknowleged(id) {
        def msg = getMessage(id)
        msg?.markAsAcknowleged()
    }
}
