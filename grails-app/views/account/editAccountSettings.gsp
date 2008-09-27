<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Edit account settings: ${loggedInMember.name} /></title>
    <meta name="layout" content="grailscrowd" />
</head>


    <body id="editAccountSettings">

            <div id="nav-context">
                <h1>Edit your account settings / <g:link controller="homeRouter">Go home &raquo;</g:link></h1>
				<h6>[ <g:link class="inline-link" controller="member" action="editProfile">edit profile</g:link> ]</h6>
            </div>

            <div class="content">

                <div class="main">

                    <g:render template="/shared/messagesRenderer" model="[modelBean:formBean]" />                    

                    <g:form name="change-password-box" controller="account" action="changePassword" method="post">
                        <h2 class="page-section-header">Change your password</h2>
                        <div class="box">
                            <p><label for="oldPassword">Old password:</label><br/> <input type="password" size="30" name="oldPassword" id="oldPassword" autocomplete="off"/></p>

                            <p><label for="newPassword">New password:</label><br/> <input type="password" size="30" value="" name="newPassword"
                                                                       id="newPassword" autocomplete="off"/></p>

                            <p><label for="confirmedPassword">Confirm new password:</label><br/> <input type="password" size="30" value=""
                                                                                      name="confirmedPassword"
                                                                                      id="confirmedPassword"
                                                                                      autocomplete="off"/></p>
                        </div>
                        <br />
                        <p><g:submitButton class="btn" name="save" value="Save changes" /></p>
                    </g:form>

					<g:form name="change-email-box" controller="account" action="changeEmail" method="post">
                        <h2 class="page-section-header">Change your email</h2>						                        
						<div class="box">							
                            <p><label for="email">Email Address:</label><br/> <input type="text" size="30" name="email" id="email" value="${loggedInMember.email}"/></p>
							<span class="meta-info">Associate this email address with an avatar for free at <a href="http://gravatar.com/" target="_blank">gravatar.com</a></span>
                        </div>
                        <br />
                        <p><g:submitButton class="btn" name="save" value="Save changes" /></p>
                    </g:form>

                    <g:form name="change-privacy-box" controller="account" action="changePrivacy" method="post">
                        <h2 class="page-section-header">Change your privacy settings</h2>
                        <div class="box">
                            <p>
                                <label for="canBeContactedViaEmail">Allow people to contact me via email:</label>
                                <g:checkBox name="canBeContactedViaEmail" value="${loggedInMember.canBeContactedViaEmail}" />
                            </p>
							<p>
			                    <label for="canBeNotifiedViaEmail">Allow email notifications:</label>
								<g:checkBox name="canBeNotifiedViaEmail" id="canBeNotifiedViaEmail" value="${loggedInMember.canBeNotifiedViaEmail}" />					
			                </p>
                        </div>
                        <br />
                        <p><g:submitButton class="btn" name="save" value="Save changes" />
						<br />
						<br />						
						<g:link class="inline-link" controller="homeRouter">&laquo; Or go home</g:link>
                    </g:form>

                </div> <!-- main -->

            </div> <!-- content -->

    </body>
</html>
