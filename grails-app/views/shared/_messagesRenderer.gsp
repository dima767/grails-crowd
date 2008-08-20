<g:if test="${flash.message}">    
    <div id="${flash.messageClass}" class="${flash.messageClass}">${flash.message}</div>
</g:if>

<g:if test="${modelBean}">
    <g:hasErrors bean="${modelBean}">
        <ul class="error-box">
            <g:eachError bean="${modelBean}">
                <li><g:message error="${it}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
</g:if>

<script type="text/javascript">
    new Effect.Fade("info-box",{delay:6});
</script>