<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Mailbox: ${loggedInMember.name.encodeAsHTML()}</title>
    <meta name="layout" content="grailscrowd" />
</head>


<body id="mailbox">

    <div id="nav-context">
        <h1>Mailbox <g:if test="${mailbox.hasAnyNewMessages()}">(${mailbox.numberOfNewMessages} new)</g:if></h1>
    </div>

    <div class="content">

        <div class="main">
            <span class="content-font">
            <g:if test="${mailbox.hasAnyDisplayableMessages()}">
                <table class="data">
                    <tbody>
                    <g:each in="${mailbox.visibleMessages}" var="message">
                        <g:if test="${message.isNew()}">
                            <tr class="new">
                        </g:if>
                        <g:else>
                            <tr>
                        </g:else>
                            <td>
                                ${message.subject.encodeAsHTML()}
                            </td>
                            <td>
                                <g:link class="inline-link" controller="mailbox" action="showMessage" params="[id:message.id]">details...</g:link>    
                            </td>
                            </tr>
                     </g:each>
                     </tbody>
                </table>
            </g:if>
            <g:else>
                <h2>Your mailbox is empty</h2>
            </g:else>
            </span>

        </div> <!-- main-->

    </div> <!-- content -->

</body>
</html>