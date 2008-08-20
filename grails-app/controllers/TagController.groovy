import grailscrowd.core.*

class TagController {

    def allowedMethods = [globalCloud: 'GET', cloudForMember: 'GET']

    def globalCloud = {
        render(view: 'tagCloud', model: [tagCounts: Tagging.globalTagCounts(), navMenu: 'globalTagCloudNavigation'])
    }

    def cloudForMember = {
        def member = Member.findByName(params._name)
        if (!member) {
            render(view: '404')
            return
        }
        render(view: 'tagCloud', model: [tagCounts: Tagging.tagCountsForMember(member), member: member, navMenu: 'memberTagCloudNavigation'])
    }
}
