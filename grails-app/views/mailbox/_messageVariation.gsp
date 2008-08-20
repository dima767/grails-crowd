<g:if test="${message.isProjectParticipationInvitation()}">
    <p>
        Please use options below to either <span class="pink"><b>join</b></span> the project or <span class="pink"><b>reject</b></span> the invitation.
    </p>

    <g:form controller="projectParticipation" method="post">
        <g:hiddenField name="messageId" value="${message.id}" />
        <g:hiddenField name="projectId" value="${message.projectId}" />
        <g:hiddenField name="creator" value="${message.fromMember}" />
        <g:hiddenField name="inviteeOrRequestor" value="${loggedInMember.name}" />
        <g:actionSubmit class="btn" name="join" action="acceptParticipationInvitation" value="Join project" />
        &larr;&rarr;
        <g:actionSubmit class="btn" name="reject" action="rejectParticipationInvitation" value="Reject invitation" />
     </g:form>
</g:if>

<g:elseif test="${message.isProjectParticipationAcknowlegement()}">
    <g:form controller="mailbox" action="archiveMessage" method="post">
        <g:hiddenField name="id" value="${message.id}" />
        <input class="btn" type="submit" name="archive" id="archive" value="&laquo Archive message"/>        
     </g:form>    
</g:elseif>

<g:elseif test="${message.isProjectParticipationRequest()}">
    <p>
        Please use options below to either <span class="pink"><b>approve</b></span> the request to participate in the project or <span class="pink"><b>reject</b></span> the request.
    </p>

    <g:form controller="projectParticipation" method="post">
        <g:hiddenField name="messageId" value="${message.id}" />
        <g:hiddenField name="projectId" value="${message.projectId}" />
        <g:hiddenField name="creator" value="${loggedInMember.name}" />
        <g:hiddenField name="inviteeOrRequestor" value="${message.fromMember}" />
        <g:actionSubmit class="btn" name="approve" action="approveParticipationRequest" value="Approve request" />
        &larr;&rarr;
        <g:actionSubmit class="btn" name="reject" action="rejectParticipationRequest" value="Reject request" />
     </g:form>
</g:elseif>