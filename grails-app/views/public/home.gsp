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

        <ul id="nav-main">
            <li class="first"><g:link controller="member" action="findByName">${grailscrowd.core.Member.count()} registered member(s)</g:link></li>
            <li><g:link controller="grailsProject" action="findByName">${grailscrowd.core.GrailsProject.count()} registered project(s)</g:link></li>                    
        </ul>
		
		<div class="content">
			
			<div class="public-home-content-left">	
		
				<h4 class="page-section-header">Newest members</h4>
                
                    <span class="content-font">
                            <a class="inline-link" href="http://google.com">John Doe</a> - North Brunswick, NJ, United States of America
                            <hr />
                        <a class="inline-link" href="http://google.com">John Doe</a> - USA
                            <hr />
						<a class="inline-link" href="http://google.com">John Doe</a>
                            <hr />
						<a class="inline-link" href="http://google.com">John Doe</a> - Greater New York area
                            <hr />
						<a class="inline-link" href="http://google.com">John Doe</a>
                            <hr />
                    </span>

			</div>

			<div class="public-home-content-right">
				
				<h4 class="page-section-header">Newest projects</h4>
                
                    <span class="content-font">
                            <a class="inline-link" href="http://google.com">John Doe</a> - North Brunswick, NJ, United States of America
                            <hr />
                        <a class="inline-link" href="http://google.com">John Doe</a> - USA
                            <hr />
						<a class="inline-link" href="http://google.com">John Doe</a>
                            <hr />
						<a class="inline-link" href="http://google.com">John Doe</a> - Greater New York area
                            <hr />
						<a class="inline-link" href="http://google.com">John Doe</a>
                            <hr />
                    </span>
						
			</div>
		
		</div>

		<div id="footer">            
            <a class="inline-link" href="http://twitter.com/grailscrowd" target="_blank">Follow on Twitter</a> | <a class="inline-link" href="http://getsatisfaction.com/grailscrowd" target="_blank">Report a problem or make a suggestion </a> | <a class="inline-link" href="http://grails.org" target="_blank">Developed with Grails</a> | <a class="inline-link" href="http://github.com/dima767/grails-crowd/tree/master" target="_blank">The code for this site is open source</a>
        </div>
</body>
</html>
