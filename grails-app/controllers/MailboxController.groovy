import grailscrowd.core.*

class MailboxController extends SecureController {

    static allowedMethods = [index: 'GET', showMessage: 'GET', archiveMessage: 'POST']

    def beforeInterceptor = [action: this.&auth]

    def index = {        
        render(view: 'mailbox', model: [mailbox: freshCurrentlyLoggedInMember().mailbox])
    }

    def showMessage = {
        def msgId = 0
		try {
			msgId = params.id.toLong()
		}
		catch(NumberFormatException) {
			redirect(uri: '/notAllowed')
		}
		
		def mailbox = freshCurrentlyLoggedInMember().mailbox
		def msg = mailbox.getMessage(msgId)
		if(!(msg?.isArchived() || msg?.isAcknowleged())) {
			if(msg.isNew()) {
				mailbox.markMessageAsSeen(msgId)
			}
			renderMessageViewOrGoToMailbox(msg)
		}
		else {
			renderMessageViewOrGoToMailbox(null)
		}
    }

    def archiveMessage = {
        freshCurrentlyLoggedInMember().mailbox.markMessageAsArchived(params.id.toLong())
        redirect(controller:'mailbox')
    }

    private renderMessageViewOrGoToMailbox(msg) {        
        if (msg) {
            render(view: 'message', model: [message: msg])
			return;
        }
        else {
            redirect(controller:'mailbox')
        }
    }
}
