<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Edit profile: ${loggedInMember.name} /></title>
    <meta name="layout" content="grailscrowd" />
</head>


    <body id="editProfile">

            <div id="nav-context">
                <h1>Edit your profile / <g:link controller="member" action="viewProfile" params="[_name:member.name]">View your public profile &raquo;</g:link></h1>
				<h6>[ <g:link class="inline-link" controller="account">edit account settings</g:link> ]</h6>
            </div>

            <div class="content">

                <div class="main">

                    <g:render template="/shared/messagesRenderer" model="[modelBean:member]" />                    

                    <g:form name="personal-info" method="post" controller="member" action="updateProfile">
                        <h4 class="page-section-header">Your personal info</h4>
                        <div id="personal-info-box" class="box">
                            <p><label for="displayName">Display name:</label><br/> <input type="text" name="displayName" id="displayName"
                                    value="${member.displayName}"/>

                            <p><label for="about">About me</label> (<a href="http://hobix.com/textile/quick.html" target="_blank">Textile enabled</a>):<br/><textarea name="about" cols="53" rows="10"
                                    id="about">${member.about}</textarea></p>
                        </div>
                        <br/>

                        <h4 class="page-section-header">Location info</h4>
                        <div id="location-info-box" class="box">
							<p><label for="homeTown">Hometown (suggested convention: City, Region(State), Country):</label><br/> <input type="text" size="55" name="homeTown"
                                    id="homeTown" value="${member.homeTown}"/></p>
                            <p><label for="location">Current location (suggested convention: City, Region(State), Country):</label><br/> <input type="text" size="55" name="location"
                                    id="location" value="${member.location}"/></p>
                        </div>
                        <br/>

                        <h4 class="page-section-header">On the web info</h4>
                        <div id="social-info-box" class="box">
                            <p><label for="homePageUri">Where to find me on the Net (Blog URL, etc.):</label><br/>
                                <input type="text" size="55" name="homePageUri" id="homePageUri" value="${member.homePageUri}"/></p>
                            <p><label for="linkedInProfileUri">My LinkedIn profile URL:</label><br/>
                                <input type="text" size="55" name="linkedInProfileUri" id="linkedInProfileUri" value="${member.linkedInProfileUri}"/></p>
                            <p><label for="twitterProfileName">My Twitter profile name:</label><br/>
                                <input type="text" size="55" name="twitterProfileName" id="twitterProfileName" value="${member.twitterProfileName}"/></p>
                            <p><label for="friendFeedProfileName">My FriendFeed profile name:</label><br/>
                                <input type="text" size="55" name="friendFeedProfileName" id="friendFeedProfileName" value="${member.friendFeedProfileName}"/></p>
                            <p><label for="flickrProfileName">My Flickr profile name:</label><br/>
                                <input type="text" size="55" name="flickrProfileName" id="flickrProfileName" value="${member.flickrProfileName}"/></p>
							<p><label for="deliciousProfileName">My Delicious profile name:</label><br/>
                                <input type="text" size="55" name="deliciousProfileName" id="deliciousProfileName" value="${member.deliciousProfileName}"/></p>
							<p><label for="ohlohProfileName">My Ohloh profile name:</label><br/>
                                <input type="text" size="55" name="ohlohProfileName" id="ohlohProfileName" value="${member.ohlohProfileName}"/></p>
                        </div>
                        <br/>

                        <h4 class="page-section-header">Employment info</h4>

                        <div id="employment-info-box" class="box">
                            <p><label for="companyName">Company where I work (name):</label><br/>
                                <input type="text" size="55" name="companyName" id="companyName" value="${member.companyName}"/></p>

                            <p><label for="companyUri">Company where I work (Web URL):</label><br/>
                                <input type="text" size="55" name="companyUri" id="companyUri" value="${member.companyUri}"/></p>

                            <p><label for="availableForHire">Am I available for hire?:</label>
                                <g:checkBox name="availableForHire" value="${member.availableForHire}"/>
                            </p>
                        </div>
                        <br/>

                        <h4 class="page-section-header">Grails experience</h4>

                        <div id="experience-info-box" class="box">
                            <p><label for="usingGrailsSinceMonth">I have been using Grails since:</label><br/>
                                <g:select name="usingGrailsSinceMonth" from="${[
                                'January',
                                'February',
                                'March',
                                'April',
                                'May',
                                'June',
                                'July',
                                'August',
                                'September',
                                'October',
                                'November',
                                'December']}"
                                 value="${member.usingGrailsSinceMonth}"
                                 noSelection="['none':'-Choose month-']"/>
                                <!--TODO: the from attribute needs to be dynamic -->
                                <g:select name="usingGrailsSinceYear" from="${[
                                '2005',
                                '2006',
                                '2007',
                                '2008',
								'2009',
								'2010',
								'2011',
								'2012',
								'2013',
								'2014',
								'2015',
								'2016',
								'2017',
								'2019',
								'2020']}"
                                value="${member.usingGrailsSinceYear}"
                                noSelection="['none':'-Choose year-']"/>
                            </p>
                        </div>
                        <br/>
                        <p><input class="btn" type="submit" name="Save" id="Save" value="Save changes"/>
						<br />
						<br />                        
						<g:link class="inline-link" controller="member" action="viewProfile" params="[_name:member.name]">&laquo; Or go back to your public profile</g:link>                      
                    </g:form>
                </div>

                <div class="sub">
                       <g:if test="${member.hasGrailsAffiliations()}">
                            <h4 class="page-section-header">Your involvement with Grails:</h4>

                            <ul>
                                <g:each in="${member.affiliations}" var="affiliation">
                                    <g:form method="post" controller="grailsAffiliation" action="delete">
                                        <input type="hidden" name="id" value="${affiliation.id}"/>
                                        <li>${affiliation.type} <b style="color:red;">Delete</b>&nbsp;<input class="btn" type="image" name="delete" id="delete"
                                            value="Delete" alt="Delete" src="${createLinkTo(dir:'images',file:'delete.png')}" /></li>
                                    </g:form>
                                 </g:each>
                            </ul>
                        </g:if>

                    <hr/>
                    <h4>Add affiliation with Grails</h4>

                    <g:form name="add-affiliations" method="post" controller="grailsAffiliation" action="add">
                        <p><label for="type">Type:</label> <br/>
                            <g:select name="type" from="${[
                                'Created Grails plugin(s)',                                
                                'Created Grails open source project(s)',
								'Created software tools based on Grails',                                
								'Presented at Grails event(s)',                                
								'Wrote book(s) about Grails',
								'Wrote publication(s) about Grails',
								'Contributed to Grails project',
                                'Grails committer',
								'Work professionally with Grails',
								'Other']}"
                                noSelection="['null':'-Choose your affiliation with Grails-']"/></p>

                        <p><label for="description">Description:</label> (<a href="http://hobix.com/textile/quick.html" target="_blank">Textile enabled</a>):<br/> <textarea name="description"
                                id="description" cols="30" rows="8"></textarea></p>

                        <p><b style="color:green;">Add</b>&nbsp;<input class="btn" type="image" name="add" id="add" value="Add" src="${createLinkTo(dir:'images',file:'add.png')}"/></p>
                    </g:form>
                </div>

            </div>

    </body>
</html>
