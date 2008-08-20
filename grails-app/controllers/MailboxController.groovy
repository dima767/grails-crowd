import grailscrowd.core.*

class MailboxController extends SecureController {

    def allowedMethods = [index: 'GET', showMessage: 'GET', archiveMessage: 'POST']

    def beforeInterceptor = [action: this.&auth]

    def index = {        
        render(view: 'mailbox', model: [mailbox: freshCurrentlyLoggedInMember().mailbox])
    }

    def showMessage = {
        def msg = freshCurrentlyLoggedInMember().mailbox.markMessageAsSeen(params.id.toLong())
        renderMessageViewOrGoToMailbox(msg)
    }

    def archiveMessage = {
        freshCurrentlyLoggedInMember().mailbox.markMessageAsArchived(params.id.toLong())
        redirect(controller:'mailbox')
    }

    private renderMessageViewOrGoToMailbox(msg) {        
        if (msg) {
            render(view: 'message', model: [message: msg])
        }
        else {
            redirect(controller:'mailbox')
        }
    }
}
