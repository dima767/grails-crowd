<h1><g:link controller="member" action="viewProfile" params="[_name:menuContext.member.name]">${menuContext.member.displayName}</g:link>
/ <g:link class="inline-link" controller="tag" action="cloudForMember" params="[_name:menuContext.member.name]">Tags</g:link>
/ <span class="pink">${menuContext.tag}</span>
</h1>

&rarr; View all projects tagged with
<b><g:link class="inline-link" controller="grailsProject" action="findByTagGlobally"
            params="[selectedTag:menuContext.tag]">${menuContext.tag}</g:link></b>