import grailscrowd.core.*

class BootStrap {

    ProjectService projectService

    def init = {servletContext ->
        //Just for testing
        for (i in 1..10) {
            new Member(name: "name$i", email: "name$i@gmail.com", password: "passwd$i", displayName: "Name $i",
            about: "hello", mailbox: new Mailbox()).save(flush: true)
        }
        def creatorMember = Member.findByName("name1")		        
		def project = projectService.createProject(uri: 'http://grailscrowd.com',
                name: 'GrailsCrowd',
                description: 'Test app',
                primaryLocation: 'USA',
                architectureDescription: 'grails/mvc', tagTokens: 'grails,groovy,java'.encodeAsUniqueList(),
                creatorMember)
        if(project.hasErrors()) {
            log.info(project.errors)
        }
        projectService.createProject(uri: 'http://example1.com',
                name: 'ExampleOne',
                description: 'Example 1 project',
                primaryLocation: 'USA',
                architectureDescription: 'Rich UI (Flex) based application with Grails backend. Has Atom feeds and number of plugins',
                tagTokens: 'grails,flex,social network'.encodeAsUniqueList(),
                creatorMember)
    }

    def destroy = {
    }
} 
