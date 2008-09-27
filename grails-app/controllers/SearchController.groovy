import grailscrowd.core.*

class SearchController {

	def allowedMethods = [searchForMembersAndProjects: 'GET', searchForMembersByFirstLetter: 'GET', searchForProjectsByFirstLetter: 'GET',
						 searchForLocationsByFirstLetter: 'GET']

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
        def members = Member.findAllByDisplayNameIlike("$letter%", [sort: 'displayName'])
        render(view: '/member/list', model: [members: members, navMenu: '/member/discoverNavigationByName', letter: letter])
		
	}
	
	def searchForProjectsByFirstLetter = {
		def letter = params.letter
        def projects = GrailsProject.findAllByNameIlike("$letter%", [sort: 'name'])
        render(view: '/grailsProject/list', model: [projects: projects, navMenu: '/grailsProject/discoverNavigationByName', letter: letter])		
	}
	
	def searchForLocationsByFirstLetter = {
		def pageHeader = params.pageHeader
		def letter = params.letter
		if(pageHeader == 'Discover members by location') {
	    	def locations = Member.executeQuery("select distinct m.location \
	                                                from grailscrowd.core.Member m \
	                                                where m.location like '$letter%' \
	                                                order by m.location", [:], params)
        	render(view: '/shared/locationList', model: [locations: locations,
			   locationListHeader: pageHeader, targetController: 'member', letter: letter])
			return
		}
		else if(pageHeader == 'Discover projects by location') {
	        def locations = GrailsProject.executeQuery("select distinct p.primaryLocation \
	                                                from grailscrowd.core.GrailsProject p \
	                                                where p.primaryLocation like '$letter%' \
	                                                order by p.primaryLocation", [:], params)

	        render(view: '/shared/locationList', model: [locations: locations,
	                locationListHeader: pageHeader, targetController: 'grailsProject', letter: letter])
			return
		}
		else {
			redirect(uri: '/notAllowed')
		}
	}
	
}
