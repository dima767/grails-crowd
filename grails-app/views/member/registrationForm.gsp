<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Sign up</title>
    <meta name="layout" content="grailscrowd" />
</head>

<body id="signUp-screen" onload="document.getElementById('name').focus();">

    <div id="nav-context"/>    

    <div class="content">

            <h2>Register for a free account</h2>
            <br />

            <g:render template="/shared/messagesRenderer" model="[modelBean:member]" />
            <br />

            <div id="sign-up">
                <g:form controller="member" class="box" method="post">
                    <p>
                        <label for="name">Username:</label><br/>
                        <g:textField size="30" name="name" id="name" value="${member?.name}"/>
                    </p>

                    <p>
                        <label for="password">Password:</label><br/>
						<g:passwordField size="30" id="password" name="password" value="" autocomplete="off" />
                    </p>

                    <p>
                    <label for="confirmPassword">Confirm Password:</label><br/>
                        <g:passwordField size="30" id="confirm" name="confirm" value="" autocomplete="off" />
                    </p>
                    <p>
                        <label for="displayName">Display Name:</label><br/>
                        <g:textField size="30" name="displayName" id="displayName" value="${member?.displayName}"/>
                    </p>

                <p>
                    <label for="email">Email Address</label>:
					<br/>					
                    <g:textField size="30" name="email" id="email" value="${member?.email}"/>
					<br/>
					<br/>
					<span class="meta-info">Associate this email address with an avatar for free at <a href="http://gravatar.com/" target="_blank">gravatar.com</a></span>
                </p>

                <p>
                    <label for="canBeContactedViaEmail">Allow people to contact me via email:</label>
					<g:checkBox name="canBeContactedViaEmail" id="canBeContactedViaEmail" value="${member?.canBeContactedViaEmail}" />					
                </p>
				
				<p>
                    <label for="canBeNotifiedViaEmail">Allow email notifications:</label>
					<g:checkBox name="canBeNotifiedViaEmail" id="canBeNotifiedViaEmail" value="true" />					
                </p>

                <p><input class="btn" type="submit" name="signUp" id="signUp" value="Create an account &raquo"/></p>
            </g:form>
        </div>

      </div>

    </div>

</body>
</html>
