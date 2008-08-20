<html>
    <head>
        <title><g:message code="header.dom.title" /> <g:layoutTitle /></title>
        <meta content="text/html;charset=UTF-8" http-equiv="Content-Type"/>
        <style type="text/css" media="screen">
            /* prevent IE less than 6 from seeing CSS by adding empty comment at end of import */
            @import '${createLinkTo(dir:'css',file:'gc.css')}' /**/;
        </style>
        <g:layoutHead/>
        <g:javascript library="application"/>	
	<modalbox:modalIncludes />
	
        <link rel="shortcut icon" href="${createLinkTo(file: 'favicon.ico')}"/>
    </head>
    <body>

    <div id="wrap">
        <div id="header">
            <h1> <g:link controller="homeRouter"><img src="${createLinkTo(dir:'images',file:'grails-crowd.gif')}" width="249" height="106"
                                 alt="GrailsCrowd"/><span><g:message code="header.title" /></span></g:link></h1>

            <p id="nav-meta">
            <g:if test="${loggedInMember}">
                <g:message code="header.auth.welcome.signedin" /> <g:link controller="member" action="viewProfile" params="[_name:loggedInMember.name]">${loggedInMember.name}</g:link>
                <span>|</span> <g:if test="${loggedInMember.mailbox.hasAnyNewMessages()}"><g:link style="background-color: #fff;" controller="mailbox"><img src="${createLinkTo(dir:'images',file:'inbox-new.gif')}" alt="Inbox"/></g:link><b>(${loggedInMember.mailbox.numberOfNewMessages} new)</b></g:if>
                <g:else><g:link style="background-color: #fff;" controller="mailbox"><img src="${createLinkTo(dir:'images',file:'inbox-no-new.gif')}" alt="Inbox"/></g:link></g:else> 
                <span>|</span> (<g:link controller="authentication" action="handleLogout"><g:message code="header.auth.signout" /></g:link>)
            </g:if>
            <g:else>
                <g:message code="header.auth.welcome.signedout" /> (<g:link controller="member"><g:message code="header.auth.register" /></g:link>) <span>|</span> (<g:link controller="authentication"><g:message code="header.auth.signin" /></g:link>)
            </g:else>
            </p>

            <!-- Let's just get 1.0 out of the door. Then we could introduce search, etc., etc.
            <form id="search" action="search.html">
                <div>
                    <input type="text" value=""/>
                    <input class="btn" type="image" name="search" alt="Search" value="Search" src="${createLinkTo(dir:'images',file:'search.gif')}"/>
                </div>
            </form> -->

            <ul id="nav-main">
                <li class="first"><g:link controller="homeRouter"><g:message code="header.nav.home" /></g:link></li>
                <li><g:link controller="member" action="findRandom"><g:message code="header.nav.discover-member" /></g:link></li>
                <li><g:link controller="grailsProject" action="findRandom"><g:message code="header.nav.discover-projects" /></g:link></li>
                <g:if test="${loggedInMember}">
                    <li><g:link controller="grailsProject" action="projectCreationForm">+ Create new project entry</g:link></li>
                </g:if>    
            </ul>

        </div>

        <!-- Layout Body -->
        <g:layoutBody/>
        <!-- End Layout Body -->

        <div id="footer">            
            <a class="inline-link" href="http://twitter.com/grailscrowd" target="_blank">Follow on Twitter</a> | <a class="inline-link" href="http://getsatisfaction.com/grailscrowd" target="_blank">Report a problem or make a suggestion </a> | <a class="inline-link" href="http://grails.org" target="_blank">Developed with Grails</a> | <a class="inline-link" href="http://github.com/dima767/grails-crowd/tree/master" target="_blank">Get the source code</a>
        </div>

    </div>
    </body>
</html>
