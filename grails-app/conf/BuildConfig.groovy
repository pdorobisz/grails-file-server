grails.project.work.dir = 'target'

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
        build ":tomcat:$grailsVersion"

        build ':release:2.2.0', ':rest-client-builder:1.0.3', {
            export = false
        }

        test(":spock:0.7") {
            exclude "spock-grails-support"
            export = false
        }
    }
}
