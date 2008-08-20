<html>
    <head>
        <title>Sign in to GrailsCrowd</title>
        <meta name="layout" content="grailscrowd"/>
    </head>
    <body>

        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <p>
            <h1>You're logged out of Grails Crowd.</h1>
            <g:link controller="authentication" action="loginForm">Sign in again</g:link>
    </body>
</html>