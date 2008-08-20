class ModalboxTagLib {

    static namespace = "modalbox"
 
    def createLink = { attrs,body ->
        // By default trigger onclick, but allow onmouseover etc
        def event = attrs.event ? attrs.event : 'onclick';
 
        // linkname only supported for backwards-compatibility. Default it to empty-string
        def linkname = attrs.linkname ? attrs.linkname : ""
 
        out << """
            <a href='${g.createLink(attrs)}' title='${attrs['title']}' ${event}='Modalbox.show(this.href, {title: this.title, width: ${attrs['width']}}); return false;'>${linkname}${body.call()}</a>
        """
    }
 
    def modalIncludes = {
        def jsFolder    = createLinkTo(dir:'plugins',file:'modalbox-0.2/js/modalbox')
        def cssFolder   = createLinkTo(dir:'plugins',file:'modalbox-0.2/css')
 
        out << """
            <script type='text/javascript' src='${jsFolder}/prototype.js' ></script>
    		<script type='text/javascript' src='${jsFolder}/scriptaculous.js?load=effects'></script>
    		<script type='text/javascript' src='${jsFolder}/modalbox.js' ></script>
    		<link rel='stylesheet' href='${cssFolder}/modalbox.css' />
        """
    }

}
