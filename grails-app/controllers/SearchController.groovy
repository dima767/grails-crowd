class SearchController {

	def allowedMethods = [searchForMembersAndProjects: 'GET']

    def searchForMembersAndProjects = { 
		render 'Search'
	}
}
