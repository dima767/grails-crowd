<h4><avatar:gravatar email="${member.email}" defaultGravatarUrl="${'http://grailscrowd.com/images/default-gravatar-50.png'.encodeAsURL()}" size="50"/> <g:link controller="member" action="viewProfile" params="[_name:member.name]">${member.displayName}</g:link>
	<g:if test="${member.location}">	
		<span class="meta-info"> - ${member.location}</span>
	</g:if>
</h4>
    ${member.about?.encodeAsShortenedTo92Characters()}
