<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title><g:message code="project.view.dom.title" /> ${grailsProject.name}</title>

    <meta name="layout" content="grailscrowd" />
    <!-- <feed:meta kind="atom" version="1.0" controller="grailsProject" action="commentsFeed"/> -->
</head>


<body id="project">

    <div id="nav-context">
        <h1><g:message code="project.view.title" /> ${grailsProject.name}</h1>
		<span class="meta-info">Created on <g:niceDate date="${grailsProject.dateCreated}" /></span>
		<br />

        <g:if test="${grailsProject.creator.id == loggedInMember?.id}">
            <h6>[ <g:link class="inline-link" controller="grailsProject" action="editProject" id="$grailsProject.id"><g:message code="project.view.link.edit" /></g:link> ]</h6>
        </g:if>
    </div>

    <div class="content">

        <div class="main">

            <g:render template="/shared/messagesRenderer"/>

            <br />

            <div class="description-box">
                <span class="content-font">
                    ${grailsProject.description.encodeAsTextile()}
                </span>
            </div>

            <g:if test="${grailsProject.architectureDescription}">
                <h4 class="page-section-header">Architecture description</h4>
                <div class="transparent-box">
                    <span class="content-font">
                        ${grailsProject.architectureDescription.encodeAsTextile()}
                    </span>
                </div>
            </g:if>

            <g:if test="${grailsProject.hasAnyMiscInfo()}">
                <h4 class="page-section-header">Misc info</h4>
                <div class="transparent-box">
                    <span class="content-font">
                        <g:if test="${grailsProject.uri}">
                            <label><g:message code="project.view.label.url" />:</label>
                            <a href="${grailsProject.uri}" target="_blank">${grailsProject.uri}</a>
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${grailsProject.primaryLocation}">
                            <label><g:message code="project.view.label.location" />:</label>
                            <g:link controller="grailsProject" action="findByLocation"
                                    params="[q:grailsProject.primaryLocation]">${grailsProject.primaryLocation}</g:link>                            
                            <br />
                            <br />
                        </g:if>
                    </span>
                </div>
            </g:if>
                  
            <div id="comments">
                <h4 class="page-section-header"><g:message code="project.view.comments.title" /> (${grailsProject.comments.size()}) :</h4>

                <g:each var="comment" in="${grailsProject.comments}">               
                    	<div class="comments-box">
							<span class="meta-info">
								<g:link class="inline-link" controller="member" action="viewProfile" params="[_name:comment.member.name]">${comment.member.displayName}</g:link> on <g:niceDate date="${comment.dateCreated}" />
							</span>
							<br/>
							<br/>
                            <span class="content-font">${comment.body.encodeAsTextile()}</span>
                        </div>
						<br/>
                </g:each>
            </div>

            <g:if test="${loggedInMember}">
                <h4><g:message code="project.view.comments.form.title" />:</h4> (<a href="http://hobix.com/textile" target="_blank">Textile enabled</a>)
                <g:form name="post-comment" method="post" controller="grailsProject" action="postComment" id="${grailsProject?.id}">
                    <p><g:textArea name="body" id="comment" rows="10" cols="60"/></p>
                    <p><g:submitButton class="btn" id="PostComment" name="PostComment" value="${message(code:'project.view.comments.form.button.save')}" /></p>
                </g:form>
            </g:if>

        </div> <!-- main -->

        <div class="sub">

            <div class="transparent-box">
                <span class="content-font">
                    <label><g:message code="project.view.creator.title" />:</label>
                    <g:link class="inline-link" controller="member" action="viewProfile" params="[_name:grailsProject.creator.name]">${grailsProject.creator.displayName}</g:link>

                    <br />
                    <br />

                    <g:if test="${grailsProject.hasAnyActiveParticipants()}">
                        <label><g:message code="project.view.participants.title" />:</label>
                        <br />
                        <ul>
                            <g:each var="p" in="${grailsProject.allActiveParticipants()}">
                                <li><g:link class="inline-link" controller="member" action="viewProfile" params="[_name:p.name]">${p.displayName}</g:link></li>
                            </g:each>
                        </ul>
                    </g:if>

                    <g:if test="${grailsProject.hasAnyTags()}">
                        <label><g:message code="project.view.tags.title" />:</label>
                        <br />
                        <ul>
                            <g:each var="t" in="${grailsProject.taggings.tag}">
                                <li><g:link class="inline-link" controller="grailsProject" action="findByTagForMember" 
                                        params="[_name:grailsProject.creator.name, selectedTag:t.name]">${t.name}</g:link></li>                                
                            </g:each>
                        </ul>
                    </g:if>
                </span>
            </div>

            <br />

            <g:if test="${loggedInMember && (!grailsProject.isCreator(loggedInMember.id)) && (!loggedInMember.isAwareOf(grailsProject))}">
                <hr />
                <g:form name="request-to-participate" method="post" controller="projectParticipation" action="requestParticipation">
                    <input type="hidden" name="requestor.id" value="${loggedInMember.id}"/>
                    <input type="hidden" name="project.id" value="${grailsProject.id}"/>
                    <p><input class="btn" type="submit" name="requestParticipation" id="requestParticipation" value="Request to join as a participant"/></p>
                </g:form>
            </g:if>
			
			<hr />									
				<span class="meta-info">This project has been viewed <strong>${grailsProject.numberOfPublicViews}</strong> time(s)</span>
            
        </div> <!-- sub -->

    </div> <!-- content -->

</body>
</html>
