<html>
    <head>
        <title>Grails Crowd: Discover members (last 7 days)</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <h2>Recent members (last 7 days) (view holder)</h2>
        <g:each in="${members}" var="member">
            <ul>
                <li><g:link action="viewProfile" params="[_name:member.name]">${member.displayName}</g:link></li>
            </ul>
        </g:each>
     </body>
</html>