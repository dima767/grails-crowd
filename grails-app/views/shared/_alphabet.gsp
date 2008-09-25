<g:each var="ltr" in="${ ('A'..'Z') }">
	<g:if test="${ltr != letter}">
    	<g:link class="step" controller="search" action="${searchAction}" params="[letter:ltr]">${ltr}</g:link>
    </g:if>
    <g:else>
    	<span class="currentStep">
        	${ltr}
        </span>
    </g:else>
</g:each>
