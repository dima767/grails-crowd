import grailscrowd.core.*

class CommentController {
	
	def allowedMethods = [feed: 'GET', list: 'GET']

    def list = {
		withComments {
			[comments: it, paginatingController: controllerName, paginatingAction: actionName, total: Comment.count()]
		}
	}
	
	def feed = {
		withComments {comments ->
        	render(feedType: "atom") {
            	title = "Grails Crowd project comments"
            	description = "Comments across all projects registered on Grails Crowd"
            	link = createLink(controller: "comment", action: "feed")

            	comments.each() {comment ->
                	entry {
						title = "A comment for project ${comment.project.name} has been submitted on ${g.niceDate(date:comment.dateCreated)}"                   
						link = createLink(controller: "grailsProject", action: "viewProject", id: comment.project.id)
                    	author = comment.member.displayName
                    	publishedDate = comment.dateCreated
						content {
                        	comment.body
                    	}
                	}
            	}
        	} //render
		} //withComments
	}
	
	private withComments(callable) {
		params.order = 'desc'
		def comments = Comment.listOrderByDateCreated(params)
		callable(comments)
	}
	
}
