grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.reload.enabled = true

forkConfig = [maxMemory: 1024, minMemory: 64, debug: false, maxPerm: 512]
grails.project.fork = [
   test: forkConfig, // configure settings for the test-app JVM
   run: forkConfig, // configure settings for the run-app JVM
   war: forkConfig, // configure settings for the run-war JVM
   console: forkConfig // configure settings for the Swing console JVM
]


//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.server.port.http = 8090
grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.20'
       
       // Este error da si uso http-builder 0.6 con 0.5.2 anda ok
       // FIX: Me da un error luego de poner referencia a http-builder
       // http://jira.grails.org/browse/GPEXPORT-18?focusedCommentId=69307&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-69307
       // http://stackoverflow.com/questions/10697312/use-rest-plugin-with-grails-project
       /*
       compile (group:'org.apache.poi', name:'poi', version:'3.7') {
        excludes 'xmlbeans'
       }
       compile (group:'org.apache.poi', name:'poi-ooxml', version:'3.7') {
        excludes 'xmlbeans'
       }
       */
       
/*
       runtime('com.thoughtworks.xstream:xstream:1.4.3') {
	      // loader constraint violation: loader (instance of <bootloader>) previously
	      // initiated loading for a different type with name "org/w3c/dom/TypeInfo"
	      //excludes 'xmlbeans'
	   }
       */
	   
       runtime('org.codehaus.groovy.modules.http-builder:http-builder:0.5.2') {
         excludes "commons-logging", "xml-apis", "groovy"
       }
       
       dependencies {
        test "org.grails:grails-datastore-test-support:1.0-grails-2.4"
       }
       
       compile "mysql:mysql-connector-java:5.1.22"
    }

    plugins {
        //runtime ":hibernate:$grailsVersion"
        //runtime ":jquery:1.7.2"
        //runtime ":resources:1.2"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:7.0.52.1"

        // plugins for the compile step
        compile ':scaffolding:2.1.0'
        compile ':cache:1.1.3'
        compile ':asset-pipeline:1.8.3'

        // plugins needed at runtime but not for compilation
        runtime ':hibernate4:4.3.5.4' // or ':hibernate:3.6.10.14'
        runtime ':database-migration:1.4.0'
        runtime ':jquery:1.11.0.2'
        
        compile ':quartz:1.0.2'
    }
}
