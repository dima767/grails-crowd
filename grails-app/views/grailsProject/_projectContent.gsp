<h4 class="page-section-header">Project info</h4>
<div id="project-info-box" class="box">
    <g:if test="${mode == 'add'}">
        <p><label for="name">Project name (cannot be changed):</label><br/><br/> <input type="text" size="55" name="name" id="name"
                value="${project?.name}"/>

        <p><label for="description">Description</label> (<a href="http://hobix.com/textile" target="_blank">Textile enabled</a>):<br/><textarea name="description" cols="53" rows="10"
                id="description">${project?.description}</textarea></p>

        <p><label for="architectureDescription">Architecture description</label> (<a href="http://hobix.com/textile" target="_blank">Textile enabled</a>):<br/><textarea id="architectureDescription"
                name="architectureDescription" cols="53" rows="10">${project?.architectureDescription}</textarea></p>

        <p><label for="uri">Project URL</label><br/> <input type="text" size="55" name="uri" id="uri"
                value="${project?.uri}"/></p>

        <p><label for="primaryLocation">Where the project is developed (suggested convention: City, Region(State), Country):</label><br/> <input type="text" size="55" name="primaryLocation" id="primaryLocation"
                value="${project?.primaryLocation}"/></p>

        <p><label for="tags">Tags (comma separated):</label><br/> <input type="text" size="55" name="tags" id="tags"
                value="${project?.commaSeparatedTags}"/></p>
    </g:if>
    <g:else>
        <p><label for="description">Description</label> (<a href="http://hobix.com/textile" target="_blank">Textile enabled</a>):<br/><textarea name="description" cols="53" rows="10"
                id="description">${project?.description}</textarea></p>

        <p><label for="architectureDescription">Architecture description</label> (<a href="http://hobix.com/textile" target="_blank">Textile enabled</a>):<br/><textarea id="architectureDescription" name="architectureDescription" cols="53" rows="10"
                id="about">${project?.architectureDescription}</textarea></p>

		<p><label for="uri">Project URL</label><br/> <input type="text" size="55" name="uri" id="uri"
                value="${project?.uri}"/></p>
        
        <p><label for="primaryLocation">Where the project is developed (suggested convention: City, Region(State), Country):</label><br/> <input type="text" size="55" name="primaryLocation" id="primaryLocation"
                value="${project?.primaryLocation}"/></p>

        <p><label for="tags">Tags (comma separated):</label><br/> <input type="text" size="55" name="tags" id="tags"
                value="${project?.commaSeparatedTags}"/></p>
    </g:else>
</div>
