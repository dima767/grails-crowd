<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Create Grails project entry</title>
    <meta name="layout" content="grailscrowd" />
</head>

    <body id="projectCreationForm">

            <div id="nav-context">
                <h1>Create new Grails project entry</h1>
            </div>

            <div class="content">

                <div class="main">

                    <g:render template="/shared/messagesRenderer" model="[modelBean:project]" />

                    <g:form name="new-grails-project" method="post" controller="grailsProject" action="handleProjectCreation">
                        <g:render template="projectContent" model="[project:project,mode:'add']" />
                        <br/>
                        <p><input class="btn" type="submit" name="addProject" id="addProject" value="Add project &raquo"/></p>
                    </g:form>

                </div> <!-- main -->

            </div> <!-- content -->

    </body>
</html>
