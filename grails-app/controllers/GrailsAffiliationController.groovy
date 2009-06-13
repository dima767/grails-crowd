import grailscrowd.core.*

class GrailsAffiliationController extends SecureController {

    def beforeInterceptor = [action: this.&auth, only: ['add', 'delete']]

    static allowedMethods = [create: 'GET', save: 'POST', delete: 'POST']

    def edit = {
    }

    def add = {
		if(params.type == 'null') {
			redirect(controller: 'member', action: 'editProfile')
		}			        
		def affiliation = new GrailsAffiliation(params)
        def member = freshCurrentlyLoggedInMember()
        member.addToAffiliations(affiliation)
        redirect(controller: 'member', action: 'editProfile')
    }

    def delete = {
        def affiliation = GrailsAffiliation.get(params.id)
        //remove from association does not work for some reason, so we just delete the affiliation
        affiliation.delete()
        //freshCurrentlyLoggedInMember().removeFromAffiliations(affiliation)
        redirect(controller: 'member', action: 'editProfile')
    }

    def details = {
        def affiliation = GrailsAffiliation.get(params.id)                
		if (affiliation.description.trim()) {		
			render """<div class="description-box">${affiliation.description.encodeAsTextile()}</div><br/>"""
		}
		else {
			render 'No details available<br/>'
		}
		render """<a href="#" class="inline-link" title="Close window" onclick="Modalbox.hide(); return false;">Close X</a>"""        
    }
}
