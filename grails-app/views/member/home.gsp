<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>${loggedInMember.name.encodeAsHTML()} home</title>

    <meta name="layout" content="grailscrowd" />
</head>


<body id="member-home">

    <div id="nav-context">
        <h1>Hi there, ${loggedInMember.displayName.encodeAsHTML()}</h1>
    </div>

    <div class="content">

        <div class="main">

            <g:if test="${loggedInMember.mailbox.hasAnyNewMessages()}">
                <div class="info-box">You have
                    <g:link controller="mailbox">${loggedInMember.mailbox.numberOfNewMessages} new message(s)</g:link></div>
            </g:if>

            <br/>

            <div class="transparent-box">
                <span class="content-font">
                    <h5>Your stuff</h5>
                    <g:if test="${loggedInMember.hasAnyStuff()}">
                        <g:if test="${loggedInMember.numberOfCreatedProjects}">
                            <h5><span class="pink">&raquo;</span> <g:link class="inline-link"
                                controller="member" action="createdProjects">Projects you created</g:link> 
                                (${loggedInMember.numberOfCreatedProjects})</h5>
                        </g:if>
                        <g:if test="${loggedInMember.numberOfParticipatingInProjects}">
                            <h5><span class="pink">&raquo;</span> <g:link class="inline-link"
                                controller="member" action="participatingInProjects">Projects you are a member of</g:link>
                                (${loggedInMember.numberOfParticipatingInProjects})</h5>
                        </g:if>
                        <g:if test="${loggedInMember.hasAnyColleagues()}">
                            <h5><span class="pink">&raquo;</span> <g:link class="inline-link"
                                controller="member" action="colleagues">Your collegues</g:link>
                                (${loggedInMember.numberOfProjectColleagues})</h5>
                        </g:if>
                    </g:if>
                    <g:else>
                        You haven't created any content on Grails Crowd.
                    </g:else>
                </span>
            </div>

            <br/>

            <div class="transparent-box">
                <span class="content-font">
                    <h5>Stuff to do</h5>
                    <h6><span class="pink">&raquo;</span> <g:link class="inline-link" controller="grailsProject" action="projectCreationForm">create new project entry</g:link></h6>
                    <h6><span class="pink">&raquo;</span> <g:link class="inline-link" controller="member" action="viewProfile" params="[_name:loggedInMember.name]">view your public profile</g:link></h6>
                    <h6><span class="pink">&raquo;</span> <g:link class="inline-link" controller="member" action="editProfile">edit your profile</g:link></h6>
                    <h6><span class="pink">&raquo;</span> <g:link class="inline-link" controller="account">edit your account settings</g:link></h6>
                </span>
            </div>

        </div> <!-- main -->

     </div> <!-- content -->

</body>
</html>
