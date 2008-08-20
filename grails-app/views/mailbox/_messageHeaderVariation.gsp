<g:if test="${message.isProjectParticipationInvitation()}">
    <h1>Invitation from
        <g:link controller="member" action="viewProfile" params="[_name:message.fromMember]">${grailscrowd.core.Member.findByName(message.fromMember).displayName}</g:link>
        to join project <g:link controller="grailsProject" action="viewProject" params="[id:message.projectId]">${message.projectName}</g:link>
    </h1>
    <hr />
</g:if>

<g:elseif test="${message.isProjectParticipationAcknowlegement()}">
    <h1><g:link controller="member" action="viewProfile" params="[_name:message.fromMember]">${grailscrowd.core.Member.findByName(message.fromMember).displayName}</g:link>
        has <span class="pink">acknowleged</span> the invitation/request to join project <g:link controller="grailsProject" action="viewProject" params="[id:message.projectId]">${message.projectName}</g:link>
    </h1>
    <hr />
</g:elseif>

<g:elseif test="${message.isProjectParticipationRequest()}">
    <h1>Request from
        <g:link controller="member" action="viewProfile" params="[_name:message.fromMember]">${grailscrowd.core.Member.findByName(message.fromMember).displayName}</g:link>
        to join project <g:link controller="grailsProject" action="viewProject" params="[id:message.projectId]">${message.projectName}</g:link>
    </h1>
    <hr />
</g:elseif>