package grailscrowd.core

class Comment implements Comparable {

    String body

    // auto-timestamping
    Date dateCreated

    static belongsTo = [member: Member, project: GrailsProject]
    static constraints = {
        body(blank: false, maxSize: 4000)
    }

	public int compareTo(Object obj) {
    	return dateCreated <=> obj.dateCreated
  	}
}
