class UrlMappings {
    static mappings = {
        //No default mappings:
        //we want to use "RSETful URIs for each controller-action combination as much as possible;
        //So before implementing Controller.action closures, just add the mapping here first

        //Using explicit default action until GRAILS-3153 is fixed
        "/"(controller: 'homeRouter', action = 'index')

        "/signin"(controller: "authentication") {
            action = [GET: "loginForm", POST: "handleLogin"]
        }

        "/signup"(controller: "member") {
            action = [GET: "registrationForm", POST: "handleRegistration"]
        }

        "/signout"(controller: "authentication") {
            action = [POST: "handleLogin"]
        }

        "/signout"(controller: 'authentication', action: 'handleLogout')

        //********** MEMBERS *******************************************/
        //For some reason $name just wouldn't work, so had to use $_name
        "/members/$_name"(controller: 'member', action: 'viewProfile')

        "/members/discovered"(controller: 'member', action: 'findRandom')     

        "/members/locations"(controller: 'member', action: 'listMemberLocations')

        "/members/byName"(controller: 'member', action: 'findByName')

        "/members/byLocation"(controller: 'member', action: 'findByLocation')

        "/members/current/projects/created"(controller: 'member', action: 'createdProjects')

        "/members/current/projects/participatingIn"(controller: 'member', action: 'participatingInProjects')

        "/members/current/colleagues"(controller: 'member', action: 'colleagues')

		"/members/latest/feed/atom"(controller: 'member', action: 'latestFeed')

        //********** PROFILE *******************************************/
        "/profile/current/updatable"(controller: "member", action: "editProfile") //GET form representation

        "/profile/updatable/current"(controller: "member", action: "updateProfile") //POST (for now)

        //********** ACCOUNT SETTINGS *******************************************/
        "/account/settings/current/updatable"(controller: "account", action: "edit") //GET form representation

        "/account/settings/updatable/current"(controller: "account", action: "update") //POST (for now)

		"/account"(controller: 'account', action = 'index') //GET

        "/account/password"(controller: 'account', action: 'changePassword') //POST

        "/account/privacy"(controller: 'account', action: 'changePrivacy') //POST

		"/account/email"(controller: 'account', action: 'changeEmail') //POST

        //********* AFFILIATIONS ****************************************/
        "/affiliations/appendable"(controller: "grailsAffiliation", action: "edit") //GET form representation

        "/affiliations"(controller: "grailsAffiliation", action: "add") //POST for now

        "/affiliations/removable"(controller: "grailsAffiliation", action: "delete") //POST for now

		"/affiliations/${id}"(controller: "grailsAffiliation", action: "details")

        //********* PROJECTS *************************************************/
        "/projects/discovered"(controller: 'grailsProject', action: 'findRandom')

        "/projects/locations"(controller: 'grailsProject', action: 'listProjectLocations')

        "/projects/byName"(controller: 'grailsProject', action: 'findByName')

        "/projects/byLocation"(controller: 'grailsProject', action: 'findByLocation')

        "/projects/byTag"(controller: 'grailsProject', action: 'findByTagGlobally')

        "/members/$_name/projects/byTag"(controller: 'grailsProject', action: 'findByTagForMember')

        "/member/current/projects/byTag"(controller: 'grailsProject', action: 'findByTagForMemberWrapper')

        //"/projects/team/$min/$max"(controller: 'grailsProject', action: 'findByTeamCount')

        "/projects/appendable"(controller: 'grailsProject', action: 'projectCreationForm') //GET

        "/projects"(controller: 'grailsProject', action: 'handleProjectCreation') //POST

        "/projects/$id"(controller: 'grailsProject', action: 'viewProject') //GET

        "/projects/$id/updatable"(controller: 'grailsProject', action: 'editProject') //GET form representation

        "/projects/updatable/$id"(controller: 'grailsProject', action: 'updateProject') //POST

		"/projects/latest/feed/atom"(controller: 'grailsProject', action: 'latestFeed')

        //********* COMMENTS **********************************************************/        
        "/projects/$id/comments/feed/atom"(controller: 'grailsProject', action: 'commentsFeed') //GET

        "/projects/$id/comments"(controller: 'grailsProject', action: 'postComment') //POST

		"/comments"(controller: 'comment', action: 'list') //GET

        //********* PROJECT PARTICIPATION **********************************************************/
        "/projects/current/participations"(controller: 'projectParticipation', action: 'index') //POST

        "/projects/current/participations/invitations"(controller: 'projectParticipation', action: 'invite') //POST

        "/projects/current/participations/invitations/acceptance"(controller: 'projectParticipation', action: 'acceptParticipationInvitation') //POST

        "/projects/current/participations/invitations/rejection"(controller: 'projectParticipation', action: 'rejectParticipationInvitation') //POST

        "/projects/current/participations/requests"(controller: 'projectParticipation', action: 'requestParticipation') //POST

        "/projects/current/participations/requests/approval"(controller: 'projectParticipation', action: 'approveParticipationRequest') //POST

        "/projects/current/participations/requests/rejection"(controller: 'projectParticipation', action: 'rejectParticipationRequest') //POST

        //********* TAGS **********************************************************/
        "/tags"(controller: 'tag', action: 'globalCloud') //GET

        "/members/$_name/tags"(controller: 'tag', action: 'cloudForMember') //GET

        //********* MAILBOX **********************************************************/
        //Using explicit default action until GRAILS-3153 is fixed
        "/mailbox"(controller: 'mailbox', action = 'index') //GET

        "/mailbox/messages/$id"(controller: 'mailbox', action: 'showMessage') //GET

        "/mailbox/messages/archive"(controller: 'mailbox', action: 'archiveMessage') //POST

        //********* EXCEPTIONS ******************************************************/
        //Uncomment for production
        "500"(controller:"errors", action:"serverError")
        "404"(controller:"errors", action:"notFound")
        "405"(controller:"errors", action:"notAllowed")
        "/notAllowed"(controller:"errors", action:"notAllowed")


    }
}
