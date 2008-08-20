class HomeRouterController extends SecureController {

    def allowedMethods = [index: 'GET']

    def index = {
        if (loggedIn()) {
            //TODO: prepare member's data for his/her 'home' (dashboard) view (similar to flickr)
            render(view: '/member/home', model: [loggedInMember: freshCurrentlyLoggedInMember()])
            return
        }
        else {
            //TODO: prepare public data for public 'home' view (similar to flickr)
            render(view: '/public/home')}
    }
}