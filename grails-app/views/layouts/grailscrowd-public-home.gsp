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
        <g:javascript library="scriptaculous"/>
        <link rel="shortcut icon" href="${createLinkTo(file: 'favicon.ico')}"/>
    </head>
    <body>

    <div id="wrap">
        <!-- Layout Body -->
        <g:layoutBody/>
        <!-- End Layout Body -->

		<div id="footer">            
            <a class="inline-link" href="http://twitter.com/grailscrowd" target="_blank">Follow on Twitter</a> | <a class="inline-link" href="http://github.com/dima767/grails-crowd/issues" target="_blank">Report a problem or request a feature</a> | <a class="inline-link" href="http://grails.org" target="_blank">Developed with Grails</a> | <a class="inline-link" href="http://github.com/dima767/grails-crowd/tree/master" target="_blank">The code for this site is open source</a>
        	<br />
			<br />
			Hosting provided by <br /><a href="http://groovymag.com/?gc" target="_blank"><img src="${createLink(base:'http://www.groovymag.com/images/hostedby.png')}" alt="Groovymag"/></a>
		</div>

    </div>

    </body>
</html>
