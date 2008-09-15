<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Comments timeline</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="list-comments">

    <div id="nav-context">
        <h1>Comments across all projects <g:link controller="comment" action="feed"><img title="Sibscribe to the comments feed" border="0" src="${createLinkTo(dir:'images', file:'feed.png')}" alt="Atom Feed"/></g:link></h1>
    </div>

    <div class="content">

        <div class="main">
            
			<span class="content-font">
                <g:each in="${comments}" var="comment">
                    <div class="comments-box">
						<span class="meta-info">
							<g:link class="inline-link" controller="member" action="viewProfile" params="[_name:comment.member.name]">${comment.member.displayName}</g:link> for project <g:link class="inline-link" controller="grailsProject" action="viewProject" id="${comment.project.id}">${comment.project.name}</g:link>, <g:niceAgoDate date="${comment.dateCreated}" />
						</span>
						<br/>
						<br/>
	                    <span class="content-font">${comment.body.encodeAsTextile()}</span>
	                </div>
					<br/>
                </g:each>
            </span>

         </div>

    </div>

	<br />            
    <g:if test="${paginatingController && paginatingAction}">
    	<g:render template="/shared/paginator" model="[controller:paginatingController, action:paginatingAction, total:total]"/>
    </g:if>

</body>
</html>
