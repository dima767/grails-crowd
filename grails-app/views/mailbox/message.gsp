<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Message for: ${loggedInMember.name.encodeAsHTML()}</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="message">

    <div id="nav-context">
        <g:render template="messageHeaderVariation" model="[message:message]" />
    </div>

    <div class="content">

        <div class="main">

            <span class="content-font">
                <p>
                    ${message.body.encodeAsHTML()}                    
                </p>
                <g:render template="messageVariation" model="[message:message]" />
                <g:link class="inline-link" controller="mailbox">&laquo; Go back to mailbox</g:link>
          </span>

        </div> <!-- main -->

    </div> <!-- content -->    

</body>
</html>