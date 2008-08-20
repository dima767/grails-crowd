package grailscrowd.core

class Endorsement {

    String text
    String byMemberName
    static belongsTo = [member:Member]

    static constraints = {
        text(blank: false, maxSize: 500)
    }
}
