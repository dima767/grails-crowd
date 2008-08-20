package grailscrowd.core
class GrailsAffiliation {

    String description
    String type

    static belongsTo = [member: Member]

    static constraints = {
        type(blank: false)
        description(maxSize: 4000)
    }
} 
