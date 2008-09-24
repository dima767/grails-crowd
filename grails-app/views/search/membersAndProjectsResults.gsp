<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Members and Projects search results</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="members-and-projects-results">

    <g:if test="${!projects && !members}">
		<div id="nav-context">
        	<h1>Nothing found for search term <span class="pink">${searchTerm}</span></h1>
    	</div>
	</g:if>
	<g:else>
    	
		<div id="nav-context">
        	<h1>${members.size()} member(s) found for search term <span class="pink">${searchTerm}</span></h1>
    	</div>

		<div class="content">

        	<div class="main">
            	<span class="content-font">
                	<g:each in="${members}" var="member">
	                    <g:render template="/shared/memberLinkAndShortenedAbout" model="[member:member]" />                    
	                </g:each>
            	</span>            
         	</div>

    	</div>

		<hr style="{height:3px"} />

		<div id="nav-context">
        	<h1>${projects.size()} project(s) found for search term <span class="pink">${searchTerm}</span></h1>
    	</div>

		<div class="content">

        	<div class="main">
            	<span class="content-font">
					<g:each in="${projects}" var="project">
	                    <g:render template="/shared/projectLinkAndShortenedDescription" model="[project:project]" />
	                    <hr />
	                </g:each>
				</span>
			</div>
		
		</div>
	</g:else>

</body>
</html>
