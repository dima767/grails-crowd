<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Locations</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="list-locations">

    <div id="nav-context">
        <h1>${locationListHeader}</h1>
    </div>

	<br />
	<g:render template="/shared/alphabet" model="[searchAction:'searchForLocationsByFirstLetter']"/>

    <div class="content">

        <div class="main">
            <span class="content-font">
                <g:each in="${locations}" var="location">
                    <h4><g:link controller="${paginatingController}" action="findByLocation" params="[q:location]">${location}</g:link></h4>
                    <hr />
                </g:each>
            </span>
         
		</div>

    </div>
	
	<br />
    <g:render template="/shared/paginator" model="[controller:paginatingController, action:paginatingAction, total:total]"/>

</body>
</html>
