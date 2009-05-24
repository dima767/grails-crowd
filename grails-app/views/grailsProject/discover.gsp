<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Discover projects: ${project.name.encodeAsHTML()}</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="discover-projects">

    <div id="nav-context">
        <g:render template="discoverNavigationMain"/>
    </div>

    <div class="content">

        <div class="main">
            <span class="content-font">
                <g:render template="/shared/projectLinkAndShortenedDescription" model="[project:project]" />
            </span>
        </div>

    </div>

</body>
</html>