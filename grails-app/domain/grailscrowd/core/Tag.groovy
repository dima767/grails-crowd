package grailscrowd.core
class Tag {

    String name
    
    static constraints = {
        name(blank: false, maxSize: 255)
        name(unique: true)
    }
} 