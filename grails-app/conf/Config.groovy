// log4j configuration
log4j {
    appender.stdout = "org.apache.log4j.ConsoleAppender"
	appender.'stdout.layout'="org.apache.log4j.PatternLayout"
 	appender.'stdout.layout.ConversionPattern'='[%r] %c{2} %m%n'
    rootLogger="error,stdout"
    logger {        
        grails="debug,stdout"
        org {
            codehaus.groovy.grails.web.servlet="info,stdout"  //  controllers
			codehaus.groovy.grails.web.pages="info,stdout" //  GSP
        	codehaus.groovy.grails.web.sitemesh="info,stdout" //  layouts
        	codehaus.groovy.grails."web.mapping.filter"="info,stdout" // URL mapping
        	codehaus.groovy.grails."web.mapping"="info,stdout" // URL mapping
            codehaus.groovy.grails.commons="info,stdout" // core / classloading
            codehaus.groovy.grails.plugins="info,stdout" // plugins
            codehaus.groovy.grails.orm.hibernate="info,stdout" // hibernate integration
            springframework="off,stdout"
            hibernate="off,stdout"
        }
    }
	additivity.'default' = false
    additivity {
		grails=false
		org {
           codehaus.groovy.grails=false
           springframework=false
		   hibernate=false
		}
    }
}

grails {
   mail {
     host = "smtp.gmail.com"
     port = 465
     username = "***"
     password = "***"
     props = ["mail.smtp.auth":"true", 					   
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
	}
}

//GrailsCrowd related configuration
gc {
    uri.signin='/signin'
}

// The following properties have been added by the Upgrade process...
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"
