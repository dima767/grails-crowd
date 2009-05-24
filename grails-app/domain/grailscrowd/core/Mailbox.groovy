package grailscrowd.core

class Mailbox {
    static hasMany = [messages: Message]
    static belongsTo = [member: Member]
	
	static mapping = {
		messages fetch:'join'
	}
	


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
