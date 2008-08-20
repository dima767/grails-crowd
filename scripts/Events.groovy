import groovy.xml.StreamingMarkupBuilder

//modify the generated web.xml so that it supports being mapped to 'request' and 'errror'
eventWebXmlEnd = {String tmpfile ->
    //find the filter mapping to change
    String filterNm = "grailsWebRequest"
    def root = new XmlSlurper().parse(webXmlFile)
    def gwr = root."filter-mapping".find { it."filter-name" == filterNm }
    if (!gwr.size()) throw new RuntimeException("[fail] No Filter named $filterNm")
    if (gwr.dispatcher.size()) throw new RuntimeException("[fail] Dispatchers exist for $filterNm")

    //xml is as expected, now modify it and write it back out
    gwr.appendNode {
        dispatcher("ERROR")
        dispatcher("REQUEST")
    }
    //webXmlFile is an implicit variable created before event is invoked
    webXmlFile.text = new StreamingMarkupBuilder().bind {
        mkp.declareNamespace("": "http://java.sun.com/xml/ns/j2ee")
        mkp.yield(root)
    }
}
