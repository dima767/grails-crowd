<h4><g:link controller="grailsProject" action="viewProject" id="${project.id}">${project.name.encodeAsHTML()}</g:link>
	<g:if test="${project.primaryLocation}">	
		<span class="meta-info"> - ${project.primaryLocation.encodeAsHTML()}</span>
	</g:if>
</h4>
${project.description?.encodeAsHTML()?.encodeAsShortenedTo92Characters()}
