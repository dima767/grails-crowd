import grailscrowd.core.*

class LoggedInMemberFilters {

    def filters = {
        exposeLoggedInMember(controller: '*', action: '*') {
            after = {model ->
                if (session.memberId) {
                    if(model) {
                        model.loggedInMember = Member.get(session.memberId)
                    }
                    else {                        
                        model = [loggedInMember:Member.get(session.memberId)]
                    }
                }
            }
        }
    }
}