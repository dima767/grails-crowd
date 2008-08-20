<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>GrailsCrowd: ${messageHeader}</title>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type"/>
</head>


<body id="friendly-error">
        <g:applyLayout name="grailscrowd-public-home">
            <img src="${createLinkTo(dir:'images',file:'grails-crowd-big.jpg')}" alt="${messageHeader}"/>
            <div class="content" style="position:absolute; top:50px; left:1px;">
                <h1>${messageHeader}</h1>
                <p class="content-font">
                    &nbsp;${messageBody}
                </p>
                &nbsp;<g:link controller="homeRouter">&laquo; Return to the home page</g:link>
            </div>
        </g:applyLayout>
</body>
</html>