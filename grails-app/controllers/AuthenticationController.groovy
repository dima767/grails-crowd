import grailscrowd.core.*

class AuthenticationController extends ControllerSupport {

    def allowedMethods = [loginForm: 'GET', handleLogin: 'POST']

    def loginForm = {
        if (session.memberId) {
            redirectToAppropriateControllerAndActionForLoggedInMember()
        }
    }

    def handleLogin = {
        if (params.name && params.password) {           
            def member = Member.findByNameAndPassword(params.name, params.password.encodeAsEncryptedPassword())
            if (member) {
                session.memberId = member.id
				member.lastLogin = new Date()
                redirectToAppropriateControllerAndActionForLoggedInMember()
            }
            else {
                handleInvalidLogin()
            }
        }
        else {
            render(view: 'loginForm')
        }
    }

    def handleLogout = {
        if (session.memberId) {
            session.memberId = null
        }
        redirect(uri: '/')
    }

    private def redirectToAppropriateControllerAndActionForLoggedInMember() {
        def redirectParams = session.originalRequestParams
        if (redirectParams) {
            session.originalRequestParams = null
            redirect(redirectParams)
        }
        else {
            redirect(uri: '/')
        }
    }

    private def handleInvalidLogin() {
        onUpdateAttempt 'Incorrect login/password combination. Please try again.', false        
        render(view: 'loginForm')
    }
}
