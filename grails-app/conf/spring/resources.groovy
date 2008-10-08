import org.springframework.jmx.support.MBeanServerFactoryBean
import org.springframework.jmx.export.MBeanExporter
import org.hibernate.jmx.StatisticsService
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler
import org.springframework.jmx.export.naming.MetadataNamingStrategy



// Place your Spring DSL code here
beans = {
    //-------------------------------------------
    // jmx stuff
    //-------------------------------------------

    mbeanServer(MBeanServerFactoryBean){
           locateExistingServerIfPossible = true
       }
    hibernateStatistics(StatisticsService){sessionFactory->
        statisticsEnabled = true
        this.sessionFactory = sessionFactory
    }

    jmxAttributeSource(AnnotationJmxAttributeSource){}

    assembler(MetadataMBeanInfoAssembler){
        attributeSource = jmxAttributeSource
    }

    namingStrategy(MetadataNamingStrategy){
        attributeSource = jmxAttributeSource
    }

    exporter(MBeanExporter){
        server = mbeanServer
        assembler = assembler
//        namingStrategy = namingStrategy
        beans = ['gc.hibernate:name=hibernate.statistics':hibernateStatistics,
        ]
    }

}