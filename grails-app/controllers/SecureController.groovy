import grailscrowd.core.*

abstract class SecureController extends ControllerSupport {

    protected def auth() {
        if (!session.memberId) {
            def originalRequestParams = [controller: controllerName, action: actionName, params: params]
            session.originalRequestParams = originalRequestParams
            redirect(uri:"/signin")
            return false
        }
    }

    protected def loggedIn() {
        null != session.memberId
    }

    protected def freshCurrentlyLoggedInMember() {
        Member.get(session.memberId)
    }    
}