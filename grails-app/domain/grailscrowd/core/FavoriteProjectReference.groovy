package grailscrowd.core

class FavoriteProjectReference {

    static belongsTo = [project: GrailsProject, member: Member]        
}
