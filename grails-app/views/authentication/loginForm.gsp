<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title><g:message code="authentication.login.dom.title" /></title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="signin" onload="document.getElementById('name').focus();">

    <div id="nav-context">
        <h2><g:message code="authentication.login.title" /></h2>
    </div>


    <div class="content">

        <g:render template="/shared/messagesRenderer" model="[modelBean:member]" />
        <br/>

        <div id="sign-in">

            <g:form controller="authentication" class="box">
                <p>
                    <label for="name"><g:message code="authentication.login.form.username" />:</label><br/>
                    <input type="text" size="30" value="" name="name" id="name" />
                </p>
                <p>
                    <label for="password"><g:message code="authentication.login.form.password" />:</label><br/>
                     <input type="password" size="30" value="" name="password" id="password" autocomplete="off" />
                </p>

                <p>
                    <input type="submit" class="btn" name="sign-in" id="sign-in-btn" value="${message(code:"authentication.login.form.button")}" />
                </p>

            </g:form>

            <h2><g:message code="authentication.login.register.title" /></h2>
            <g:link controller="member" class="sign-up"><g:message code="authentication.login.register.link" /><span/></g:link>

        </div>

    </div>

</body>
</html>
