<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Project tags on Grails Crowd</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="tag-cloud">

    <div id="nav-context">
        <g:render template="${navMenu}" model="${menuContext}"/>
    </div>

    <div class="content">

        <div class="main">
            <span class="content-font">
                <g:if test="${member}">
                    <g:set var="correctAction">findByTagForMemberWrapper</g:set>
                </g:if>
                <g:else>
                    <g:set var="correctAction">findByTagGlobally</g:set>
                </g:else>
                <richui:tagCloud values="${tagCounts}" linkClass="inline-link"
                        controller="grailsProject" action="${correctAction}" minSize="10" maxSize="27"/>
             </span>
         </div>

    </div>

</body>
</html>
