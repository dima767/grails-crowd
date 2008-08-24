<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Member: ${member.name}</title>

    <meta name="layout" content="grailscrowd" />    
</head>


<body id="profile">

    <div id="nav-context">
        <!-- TODO: encapsulate these type of checks in taglib? -->
        <g:if test="${loggedInMember?.id == member.id}">
            <h1><avatar:gravatar email="${member.email}" defaultGravatarUrl="${createLinkTo(dir:'images', file:'default-gravatar-80.png', absolute:'true').encodeAsURL()}" size="80"/> ${member.name} / ${member.displayName}</h1>			
            <h6>[ <g:link class="inline-link" controller="member" action="editProfile">edit profile</g:link> ] | [ <g:link class="inline-link" controller="account">edit account settings</g:link> ]</h6>
        </g:if>
        <g:else>
            <h1><avatar:gravatar email="${member.email}" defaultGravatarUrl="${createLinkTo(dir:'images', file:'default-gravatar-80.png', absolute:'true').encodeAsURL()}" size="80"/> Member: ${member.displayName}</h1>
        </g:else>
		<span class="meta-info">Joined on <g:niceDate date="${member.joinedOn}" /></span>
		<br />
        &rarr; <g:link class="inline-link" controller="tag" action="cloudForMember" params="[_name:member.name]">Tags</g:link>
    </div>

    <div class="content">

        <div class="main">

            <div class="description-box">
                <span class="content-font">
                <g:if test="${member.about}">
                    ${member.about.encodeAsTextile()}
                </g:if>
                <g:else>
                    My name is <b>${member.displayName}</b> and I'm now a part of the friendly Grails community! I haven't had a chance to write a few words about myself yet. 
                </g:else>
                </span>
            </div>

            <g:if test="${member.homeTown}">
                <h4 class="page-section-header">Hometown</h4>
                <p class="transparent-box">
                    <span class="content-font">
                        <label>I am originally from:</label> ${member.homeTown}
                    </span>
                </p>
            </g:if>

			<g:if test="${member.location}">
                <h4 class="page-section-header">Current location</h4>
                <p class="transparent-box">
                    <span class="content-font">
                        <label>I currently live in:</label>
						<g:link controller="member" action="findByLocation"
                                    params="[q:member.location]">${member.location}</g:link>
                    </span>
                </p>
            </g:if>

            <g:if test="${member.hasAnyOtherProfiles()}">
                <h4 class="page-section-header">Other web profiles</h4>
                <p class="transparent-box">
                    <span class="content-font">
                        <g:if test="${member.homePageUri}">
                            <label>Personal web resource (Blog, home page, etc.):</label> <a href="${member.homePageUri}" target="_blank">${member.homePageUri}</a>
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${member.linkedInProfileUri}">
                            <label>LinkedIn profile:</label> <a href="${member.linkedInProfileUri}" target="_blank">${member.linkedInProfileUri}</a>
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${member.twitterProfileUri}">
                            <label>Twitter profile:</label> <a href="${member.twitterProfileUri}" target="_blank">${member.twitterProfileUri}</a>
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${member.friendFeedProfileUri}">
                            <label>FriendFeed profile:</label> <a href="${member.friendFeedProfileUri}" target="_blank">${member.friendFeedProfileUri}</a>
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${member.flickrProfileUri}">
                            <label>Flickr photos:</label> <a href="${member.flickrProfileUri}" target="_blank">${member.flickrProfileUri}</a>
                            <br />
                            <br />
                        </g:if>
						<g:if test="${member.deliciousProfileUri}">
                            <label>Delicious bookmarks:</label> <a href="${member.deliciousProfileUri}" target="_blank">${member.deliciousProfileUri}</a>
                            <br />
                            <br />
                        </g:if>
						<g:if test="${member.ohlohProfileUri}">
                            <label>Ohloh profile:</label> <a href="${member.ohlohProfileUri}" target="_blank">${member.ohlohProfileUri}</a>
                            <br />
                            <br />
                        </g:if>
                    </span>
                </p>
            </g:if>

            <g:if test="${member.hasAnyEmploymentInfo()}">
                <h4 class="page-section-header">Employment</h4>
                <p class="transparent-box">
                    <span class="content-font">
                        <g:if test="${member.companyName}">
                            <label>Company:</label> ${member.companyName}
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${member.companyUri}">
                            <label>Company URL:</label> <a href="${member.companyUri}" target="_blank">${member.companyUri}</a>
                            <br />
                            <br />
                        </g:if>
                        <g:if test="${member.availableForHire}">
                            I am interested in various employment opportunities.
                            <!-- <g:if test="${loggedInMember && loggedInMember.id != member.id}"><g:link class="inline-link" controller="mailbox" action="composeEmploymentOpportunity" forMember="${member.name}">[Contact me]</g:link></g:if>
                            <g:else>
                                <span class="invisible">${session.memberToBeContacted = member.name}</span>
                                <g:link class="inline-link" controller="authentication">Sign in</g:link> so you could contact me directly.
                            </g:else> -->
                        </g:if>
                    </span>
                </p>
            </g:if>

            <g:if test="${member.canBeContactedViaEmail}">
                <br />
                <hr />
                <br />
                <p class="transparent-box">
                    <span class="content-font">
                        You can contact me directly. My email address is: <b><a class="inline-link" href="mailto:${member.email}">${member.email}</a></b>
                    </span>
                </p>
            </g:if>

        </div>

        <div class="sub">

            <g:if test="${member.hasAnyInvolvementWithGrails()}">
                <h4 class="page-section-header">Involvement with Grails:</h4>
                <div class="transparent-box">
                    <span class="content-font">
                    <g:if test="${member.hasSinceInfo()}">
                        <label>I've been using Grails since:</label> <g:if test="${member.usingGrailsSinceMonth}">${member.usingGrailsSinceMonth}</g:if> &nbsp;${member.usingGrailsSinceYear}
                        <br />
                        <br />
                    </g:if>
                    <g:if test="${member.hasGrailsAffiliations()}">
                        <label>So, I:</label>
                        <br />
                        <ul >
                            <g:each in="${member.affiliations}" var="affiliation">
                                <li><modalbox:createLink controller="grailsAffiliation" action="details" id="${affiliation.id}" title="Grails affiliation details" width="500">${affiliation.type}</modalbox:createLink>
                            </g:each>
                        </ul>
                     </g:if>
                     </span>
                </div>
            </g:if>

            <g:if test="${member.participatesInAnyProjects()}">
                <h4 class="page-section-header">Grails projects I'm involved with:</h4>
                <div class="transparent-box">
                    <span class="content-font">
                       <ul >
                            <g:each in="${member.activeProjects}" var="project">
                                <li>${project.relationshipRoleFor(member)} <g:link class="inline-link" controller="grailsProject" action="viewProject" id="${project.id}">${project.name}</g:link></li>
                            </g:each>
                        </ul>
                    </span>
                </div>
            </g:if>

            <g:if test="${member.hasAnyColleagues()}">
                <h4 class="page-section-header">My Grails colleagues:</h4>
                <div class="transparent-box">
                    <span class="content-font">
                       <ul >
                            <g:each in="${member.projectColleagues}" var="colleague">
                                <li><g:link class="inline-link" controller="member" action="viewProfile" params="[_name:colleague?.name]">${colleague?.displayName}</g:link></li>
                            </g:each>
                        </ul>
                    </span>
                </div>
            </g:if>

            <g:if test="${loggedInMember && loggedInMember.id != member.id && loggedInMember.createdAnyProjects()}">
                <hr />
                <h5>Invite ${member.displayName} to participate in my project(s):</h5>

                <g:form name="invite-to-participate" method="post" controller="projectParticipation" action="invite">
                        <p><label for="Project">Project:</label> <br/>
                            <input type="hidden" name="invitee.id" value="${member.id}"/>
                            <g:select id="project.id" name='project.id'
                             noSelection="${['null':'Select Project...']}"
                             from='${loggedInMember.createdProjects}'
                             optionKey="id" optionValue="name"/></p>
                        <p><input class="btn" type="submit" name="invite" id="invite" value="Invite"/></p>
                </g:form>
             </g:if>
			 
				<g:if test="${loggedInMember?.id == member.id}">                
					<hr />					
					<span class="meta-info">Your profile has been viewed <strong>${member.numberOfPublicViews}</strong> time(s)</span>
				</g:if>

        </div> <!-- sub -->

     </div> <!-- content -->

</body>
</html>
