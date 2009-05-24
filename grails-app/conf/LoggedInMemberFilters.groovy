import grailscrowd.core.*

class LoggedInMemberFilters {

    def filters = {
        exposeLoggedInMember(controller: '*', action: '*') {
            after = {model ->
                if (session.memberId) {
                    def member = Member.get(session.memberId)
					if(model) {
                        model.loggedInMember = member
                    }
                    else {                        
                        model = [loggedInMember:member]
                    }
                }
            }
        }
    }
}