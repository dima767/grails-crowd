import grailscrowd.core.*

class ProjectService {

    def createProject(args, creator) {
        if (!creator) {
            throw new IllegalArgumentException("Cannot create project without a creator")
        }

        def project = new GrailsProject(uri: args.uri, name: args.name, description: args.description, primaryLocation: args.primaryLocation,
                architectureDescription: args.architectureDescription)

        project.creatorMemberId = creator.id
        args.tagTokens.each {
            project.addToTaggings(Tagging.createFor(creator, it, project))
        }
        if (!project.save()) {
            return project
        }
        ProjectParticipation.active(creator, project)
        return project
    }

    def updateProject(project, args, creator) {
        project.properties = args

        // handle project tags
        def tagTokens = args.tags?.encodeAsUniqueList()

        // first remove tags that aren't there anymore
        def tagsToRemove = project?.taggings.tag.name - tagTokens
        project.taggings.findAll {
            tagsToRemove.contains(it.tag.name)
        }.each {
            project.removeFromTaggings(it)
            it.delete()
        }

        // don't create new Taggings for tags we already have
        (tagTokens - project?.taggings.tag.name).each {
            project.addToTaggings(Tagging.createFor(creator, it, project))
        }
    }
}