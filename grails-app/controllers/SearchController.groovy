import grailscrowd.core.*

class SearchController {

	def allowedMethods = [searchForMembersAndProjects: 'GET', searchForMembersByFirstLetter: 'GET']

    def searchForMembersAndProjects = { 
		def searchTerm = params.q?.trim()
		if(!searchTerm || searchTerm.size() == 0) {
			redirect(uri: '/')
		}
		def members = Member.findAllByNameIlikeOrDisplayNameIlike("%$searchTerm%", "%$searchTerm%", [sort:'displayName'])
		def projects = GrailsProject.findAllByNameIlike("%$searchTerm%", [sort:'name'])
		render(view:'membersAndProjectsResults', model: [searchTerm: searchTerm, members: members, projects: projects])
	}
	
	def searchForMembersByFirstLetter = {
		def letter = params.letter
		def total = Member.countByDisplayNameIlike("$letter%")
        def members = Member.findAllByDisplayNameIlike("$letter%", [sort: 'displayName'])
        render(view: '/member/list', model: [members: members, navMenu: '/member/discoverNavigationByName',
                paginatingController: controllerName, paginatingAction: actionName, total: total, letter: letter])
		
	}
}
