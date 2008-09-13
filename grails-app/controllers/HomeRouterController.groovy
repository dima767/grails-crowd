import grailscrowd.core.*

class HomeRouterController extends SecureController {

    def allowedMethods = [index: 'GET']

    def index = {
        if (loggedIn()) {
            render(view: '/member/home', model: [loggedInMember: freshCurrentlyLoggedInMember()])
            return
        }
        else {
			//5 (at the most) newest members and projects registered within the last 7 days			
			def newestMembers = Member.findAllByJoinedOnBetween(new Date() - 1, new Date(), [max:5, sort:"joinedOn", order:"desc"])            
			def newestProjects = GrailsProject.findAllByDateCreatedBetween(new Date() - 1, new Date(), [max:5, sort:"dateCreated", order:"desc"])
			render(view: '/public/home', model: [newestMembers: newestMembers, newestProjects: newestProjects])
		}
    }
}
