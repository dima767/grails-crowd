<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Members</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="list-members">

    <div id="nav-context">
        <g:render template="${navMenu}" model="${menuContext}"/>
    </div>

    <div class="content">

        <div class="main">
            <span class="content-font">
                <g:each in="${members}" var="member">
                    <g:render template="/shared/memberLinkAndShortenedAbout" model="[member:member]" />                    
                </g:each>
            </span>

            <br />            
            <g:if test="${paginatingController && paginatingAction}">
                <g:render template="/shared/paginator" model="[controller:paginatingController,
                      action:paginatingAction, total:total]"/>
            </g:if>
         </div>

    </div>

</body>
</html>
