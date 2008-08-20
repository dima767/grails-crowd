abstract class ControllerSupport {

    protected onUpdateAttempt(message, success) {
        flash.messageClass = success ? 'info-box' : 'error-box'
        flash.message = message
    }

}