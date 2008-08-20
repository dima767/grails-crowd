<h4><g:link controller="grailsProject" action="viewProject" id="${project.id}">${project.name}</g:link>
	<g:if test="${project.primaryLocation}">	
		<span class="meta-info"> - ${project.primaryLocation}</span>
	</g:if>
</h4>
${project.description?.encodeAsShortenedTo92Characters()}
