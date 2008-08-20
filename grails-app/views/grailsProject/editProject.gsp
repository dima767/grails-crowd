<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Edit Grails project entry: ${project.name}</title>
    <meta name="layout" content="grailscrowd" />
</head>

    <body id="editProject">

            <div id="nav-context">
                <h1>Edit Grails project entry: <g:link class="inline-link" controller="grailsProject" action="viewProject" id="${project.id}">${project.name}</g:link></h1>
            </div>

            <div class="content">

                <div class="main">

                    <g:render template="/shared/messagesRenderer" model="[modelBean:project]" />

                    <g:form name="edit-grails-project" method="post" controller="grailsProject" action="updateProject" id="$project.id">
                        <g:render template="projectContent" model="[project:project]" />
                        <br/>
                        <p><input class="btn" type="submit" name="saveProject" id="saveProject" value="Save changes"/>
						<br />
						<br />                        
						<g:link class="inline-link" controller="grailsProject" action="viewProject" id="${project.id}">&laquo; Or cancel</g:link>
                    </g:form>

                </div> <!-- main -->

            </div> <!-- content -->

    </body>
</html>
