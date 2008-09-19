//Testing online editing on GitHub.
class AccountController extends SecureController {

    def allowedMethods = [index: 'GET', changePassword: 'POST', changePrivacy: 'POST']

    def beforeInterceptor = [action: this.&auth]

    def index = {
        //Filter will add the currently loggedIn member to the model
        render(view: 'editAccountSettings')
    }

    def changePassword = {ChangePasswordForm form ->
        if (form.hasErrors()) {
            render(view: 'editAccountSettings', model: [formBean: form])
            return
        }
        def member = freshCurrentlyLoggedInMember()
        if (member.password != form.oldPassword.encodeAsEncryptedPassword()) {
            onUpdateAttempt 'Incorrect current password. Please try again.', false
            render(view: 'editAccountSettings')
            return
        }
        if (form.newPassword != form.confirmedPassword) {
            onUpdateAttempt 'Confirmed and new passwords do not match. Please try again.', false            
            render(view: 'editAccountSettings')
            return
        }
        member.password = form.newPassword.encodeAsEncryptedPassword()
        member.save()
        onUpdateAttempt 'Password has been changed successfuly.', true
        render(view: 'editAccountSettings')
    }

    def changePrivacy = {       
        def member = freshCurrentlyLoggedInMember()        
        member.properties = params
        member.save()
        onUpdateAttempt 'Privacy settings have been changed successfuly.', true
        render(view: 'editAccountSettings')
    }

	def changeEmail = {       
        def member = freshCurrentlyLoggedInMember()        
        member.properties = params		        
		if(member.save()) {
        	onUpdateAttempt 'Email has been changed successfuly.', true
			render(view: 'editAccountSettings')
			return
		}
        else {
			render(view: 'editAccountSettings', model: [formBean: member])
		}
    }
}

class ChangePasswordForm {
    String oldPassword
    String newPassword
    String confirmedPassword

    static constraints = {
        oldPassword(blank: false)
        newPassword(blank: false, minSize: 5)
        confirmedPassword(blank: false, minSize: 5)
    }
}
