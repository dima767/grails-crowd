<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Welcome to Grails Crowd - Тhe friendly Grails community</title>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type"/>
    <meta name="layout" content="grailscrowd-public-home" />
</head>


<body id="public-home">
        <img src="images/grails-crowd-big.jpg" alt="Welcome to Grails Crowd!"/>
        <div class="content" style="position:absolute; top:50px; left:1px;">
        	<h1>Promote your Grails skills</h1>
        	<h1>Promote your Grails projects</h1>
            <h1>Discover different Grails projects</h1>
            <h1>Connect with other Grails geeks</h1>

            <br />
            <g:link controller="member" class="sign-up"><g:message code="authentication.login.register.link" /><span/></g:link>
            <h3>Already registered? Then <g:link class="inline-link" controller="authentication"><g:message code="header.auth.signin" /></g:link></h3>
        </div>

		<g:form name="search" controller="search" action="searchForMembersAndProjects" method="get">
            <div>
                <input type="text" name="q" value=""/>                
				<input class="btn" type="submit" value="Search" />
            </div>
        </g:form>

        <ul id="nav-main">
            <li class="first"><g:link controller="member" action="findByName">${grailscrowd.core.Member.count()} registered member(s)</g:link></li>
            <li><g:link controller="grailsProject" action="findByName">${grailscrowd.core.GrailsProject.count()} registered project(s)</g:link></li>                    
        </ul>
		
		<div class="content">
			
			<div class="public-home-content-left">	
		
				<g:if test="${latestMembers}">				
					<h4 class="page-section-header">Latest members <g:link controller="member" action="latestFeed"><img title="Sibscribe to the latest members feed" border="0" src="${createLinkTo(dir:'images', file:'feed.png')}" alt="Atom Feed"  /></g:link></h4>
                
                    	<span class="content-font">
                     	       <g:each in="${latestMembers}" var="member">
                    				<avatar:gravatar email="${member.email}" defaultGravatarUrl="${'http://grailscrowd.com/images/default-gravatar-50.png'.encodeAsURL()}" size="50"/> <g:link controller="member" action="viewProfile" params="[_name:member.name]">${member.displayName}</g:link>
									<g:if test="${member.location}">	
										<span class="meta-info"> - ${member.location}</span>
									</g:if>
									<hr />                    				
                				</g:each>								                        
                    	</span>
				</g:if>

			</div>

			<div class="public-home-content-right">
				
				<g:if test="${latestProjects}">				
					<h4 class="page-section-header">Latest projects <g:link controller="grailsProject" action="latestFeed"><img title="Sibscribe to the latest projects feed" border="0" src="${createLinkTo(dir:'images', file:'feed.png')}" alt="Atom Feed"  /></g:link></h4>
                
                    	<span class="content-font">
                     			<g:each in="${latestProjects}" var="project">
                    				<g:link controller="grailsProject" action="viewProject" id="${project.id}">${project.name}</g:link>
									<g:if test="${project.primaryLocation}">	
										<span class="meta-info"> - ${project.primaryLocation}</span>
									</g:if>
                    				<hr />
                				</g:each>								                        
                    	</span>
				</g:if>	
						
			</div>
		
		</div>
		
		<h4 class="page-section-header">Latest comments <g:link controller="comment" action="feed"><img title="Sibscribe to the comments feed" border="0" src="${createLinkTo(dir:'images', file:'feed.png')}" alt="Atom Feed"  /></g:link></h4>
		
		<g:each var="comment" in="${latestComments}">               
            	<div class="comments-box">
					<span class="meta-info">
						By <g:link class="inline-link" controller="member" action="viewProfile" params="[_name:comment.member.name]">${comment.member.displayName}</g:link> for project <g:link class="inline-link" controller="grailsProject" action="viewProject" id="${comment.project.id}">${comment.project.name}</g:link>, <g:niceAgoDate date="${comment.dateCreated}" />
					</span>
					<br/>
					<br/>
                    <span class="content-font">${comment.body.encodeAsTextile()}</span>
                </div>
				<br/>
        </g:each>
		<g:link class="inline-link" controller="comment" action="list">&raquo; view all comments</g:link>

</body>
</html>
