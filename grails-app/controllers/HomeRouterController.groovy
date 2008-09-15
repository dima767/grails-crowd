import grailscrowd.core.*

class HomeRouterController extends SecureController {

    def allowedMethods = [index: 'GET']

    def index = {
        if (loggedIn()) {
            render(view: '/member/home', model: [loggedInMember: freshCurrentlyLoggedInMember()])
            return
        }
        else {
			//5 (at the most) latest members, projects registered within the last 2 days			
			def latestMembers = Member.findAllByJoinedOnBetween(new Date() - 1, new Date(), [max:5, sort:"joinedOn", order:"desc"])            
			def latestProjects = GrailsProject.findAllByDateCreatedBetween(new Date() - 1, new Date(), [max:5, sort:"dateCreated", order:"desc"])
			def latestComments = Comment.listOrderByDateCreated(max:5, order:"desc")
			render(view: '/public/home', model: [latestMembers: latestMembers, latestProjects: latestProjects, latestComments: latestComments])
		}
    }
}
