log4j = {
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%m%n')
    }

    error 'org.codehaus.groovy.grails',
          'org.springframework',
          'org.hibernate',
          'net.sf.ehcache.hibernate'

    debug  'grails.app'
}

grails.plugins.fileserver.paths=["resources": "test/integration/resources/"]
grails.views.default.codec="html"
grails.views.gsp.encoding="UTF-8"
