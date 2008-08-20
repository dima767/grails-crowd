<html>
    <head>
        <title><g:message code="header.dom.title" /> <g:layoutTitle /></title>
        <meta content="text/html;charset=UTF-8" http-equiv="Content-Type"/>
        <style type="text/css" media="screen">
            /* prevent IE less than 6 from seeing CSS by adding empty comment at end of import */
            @import '${createLinkTo(dir:'css',file:'gc.css')}' /**/;
        </style>
        <g:layoutHead/>
        <g:javascript library="application"/>
        <g:javascript library="scriptaculous"/>
        <link rel="shortcut icon" href="${createLinkTo(file: 'favicon.ico')}"/>
    </head>
    <body>

    <div id="wrap">

        <!-- Layout Body -->
        <g:layoutBody/>
        <!-- End Layout Body -->

    </div>

    </body>
</html>
