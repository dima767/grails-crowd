<h4><g:link controller="member" action="viewProfile" params="[_name:member.name]">${member.displayName}</g:link>
	<g:if test="${member.location}">	
		<span class="meta-info"> - ${member.location}</span>
	</g:if>
</h4>
    ${member.about?.encodeAsShortenedTo92Characters()}
