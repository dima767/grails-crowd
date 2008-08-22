package grailscrowd.core

import grailscrowd.capability.NumberOfViewsTrackable

class Member extends NumberOfViewsTrackable implements Comparable {

    String name
    String password
    String email
    String displayName
    String homePageUri
    String about
    String location
	String homeTown
    String companyName
    String companyUri
    Boolean availableForHire = false
    Boolean canBeContactedViaEmail = false
    Date joinedOn = new Date()
    String usingGrailsSinceMonth
    String usingGrailsSinceYear
    String linkedInProfileUri
    String twitterProfileName
    String flickrProfileName
    String friendFeedProfileName
	String deliciousProfileName
	String ohlohProfileName

    private static FLICKR_BASE_URI = 'http://flickr.com'
    private static TWITTER_BASE_URI = 'http://twitter.com'
    private static FRIEND_FEED_BASE_URI = 'http://friendfeed.com'
	private static DELICIOUS_BASE_URI = 'http://delicious.com'
	private static OHLOH_BASE_URI = 'http://www.ohloh.net/accounts'

    static constraints = {
        name(blank: false, unique: true, matches: "[a-zA-Z0-9_]+")
        password(blank: false, minSize: 5)
        displayName(blank: false, maxSize: 1000)
        email(blank: false, email: true)
        homePageUri(nullable: true, url: true)
        about(nullable: true, maxSize: 4000)
        location(nullable: true, maxSize: 2000)
		homeTown(nullable: true, maxSize: 2000)
        companyName(nullable: true, maxSize: 1000)
        companyUri(nullable: true, url: true)
        usingGrailsSinceMonth(nullable: true)
        usingGrailsSinceYear(nullable: true)
        linkedInProfileUri(nullable: true, url: true)
        twitterProfileName(nullable: true)
        flickrProfileName(nullable: true)
        friendFeedProfileName(nullable: true)
		deliciousProfileName(nullable: true)
		ohlohProfileName(nullable: true)
    }

    static hasMany = [projectParticipations: ProjectParticipation, affiliations: GrailsAffiliation, comments: Comment,
            taggings: Tagging, favoriteProjects: FavoriteProjectReference, endorsements: Endorsement]
	
	SortedSet taggings    
	//static fetchMode = [projectParticipations: 'eager', affiliations: 'eager']

    Mailbox mailbox

    def beforeInsert = {
        password = password.encodeAsEncryptedPassword()
    }

    def beforeUpdate = {
        if (usingGrailsSinceMonth == 'none') {
            usingGrailsSinceMonth = null
        }
        if (usingGrailsSinceYear == 'none') {
            usingGrailsSinceYear = null
        }
        if (about?.trim()?.size() == 0) {
            about = null
        }
    }

    // Social feature: gets a list of 'colleagues' working on the same projects as *this* member
    def getProjectColleagues() {
        getActiveProjects().participants.flatten().findAll {it.participant.id != this.id && it.isActive()}.participant
    }

    def getTwitterProfileUri() {
        this.twitterProfileName != null ? "$TWITTER_BASE_URI/$twitterProfileName" : null
    }

    def getFlickrProfileUri() {
        this.flickrProfileName != null ? "$FLICKR_BASE_URI/$flickrProfileName" : null
    }

    def getFriendFeedProfileUri() {
        this.friendFeedProfileName != null ? "$FRIEND_FEED_BASE_URI/$friendFeedProfileName" : null
    }

	def getDeliciousProfileUri() {
        this.deliciousProfileName != null ? "$DELICIOUS_BASE_URI/$deliciousProfileName" : null
    }

	def getOhlohProfileUri() {
        this.ohlohProfileName != null ? "$OHLOH_BASE_URI/$ohlohProfileName" : null
    }

    def availableForHireAsString() {
        availableForHire ? 'Yes' : 'No'
    }

    def hasAnyOtherProfiles() {
        (this.homePageUri != null) || (this.linkedInProfileUri != null) || (this.twitterProfileName != null) ||
                (this.friendFeedProfileName != null) || (this.flickrProfileName != null) || (this.deliciousProfileName != null) || (this.ohlohProfileName != null)
    }

    def hasAnyInvolvementWithGrails() {
        hasGrailsAffiliations() || hasSinceInfo()
    }

    def hasGrailsAffiliations() {
        this.affiliations.size() > 0
    }

    def participatesInAnyProjects() {
        this.projectParticipations.any {
            it.isActive()
        }
    }

    def createdAnyProjects() {
        this.projectParticipations.any {
            it.project.isCreator(this.id)
        }
    }

    def getCreatedProjects() {
        getActiveProjects().findAll {it.isCreator(this.id)}
    }

    def getActiveProjects() {
        this.projectParticipations.findAll {it.isActive()}.project
    }

    def getParticipatingInProjects() {
        this.projectParticipations.findAll {it.isActive() && !(it.project.isCreator(this.id))}.project
    }

    def getNumberOfCreatedProjects() {
        getCreatedProjects().size()
    }

    def getNumberOfParticipatingInProjects() {
        getParticipatingInProjects().size()
    }

    def getNumberOfProjectColleagues() {
        getProjectColleagues().size()
    }

    def hasAnyColleagues() {
        getProjectColleagues().size() > 0
    }

    def hasSinceInfo() {
        this.usingGrailsSinceYear != null
    }

    def hasAnyEmploymentInfo() {
        (this.companyName != null) || (this.companyUri != null) || (this.availableForHire)
    }

    def isAwareOf(project) {
        this.projectParticipations.any {it.project.id == project.id}
    }

    def hasAnyStuff() {
        getNumberOfCreatedProjects() || getNumberOfParticipatingInProjects() || hasAnyColleagues()
    }

    // members are sorted alphabetically by their display name
    public int compareTo(Object obj) {
        return displayName <=> obj.displayName
    }
} 
