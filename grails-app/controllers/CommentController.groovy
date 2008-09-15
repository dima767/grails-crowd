import grailscrowd.core.*

class CommentController {
	
	def allowedMethods = [feed: 'GET', list: 'GET']

    def list = {
		params.order = 'desc'
		def total = Comment.count()
		def comments = Comment.listOrderByDateCreated(params)
		[comments: comments, paginatingController: controllerName, paginatingAction: actionName, total: total]
	}
	
	def feed = {
		//TODO
	}
}
