grails.project.work.dir = 'target'
grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'
    legacyResolve false

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0", {
            export = false
        }
    }

    plugins {
        build ":tomcat:8.0.22"

        build ':release:3.1.1', ':rest-client-builder:2.1.1', {
            export = false
        }

        test(":spock:0.7") {
            exclude "spock-grails-support"
            export = false
        }
    }
}
