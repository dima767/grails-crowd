import grailscrowd.core.*

class SearchController {

	def allowedMethods = [searchForMembersAndProjects: 'GET']

    def searchForMembersAndProjects = { 
		def searchTerm = params.q?.trim()
		if(!searchTerm || searchTerm.size() == 0) {
			redirect(uri: '/')
		}
		def members = Member.findAllByNameIlikeOrDisplayNameIlike("%$searchTerm%", "%$searchTerm%", [sort:'displayName'])
		def projects = GrailsProject.findAllByNameIlike("%$searchTerm%", [sort:'name'])
		render(view:'membersAndProjectsResults', model: [searchTerm: searchTerm, members: members, projects: projects])
	}
}
