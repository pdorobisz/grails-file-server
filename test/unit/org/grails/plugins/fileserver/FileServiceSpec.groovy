package org.grails.plugins.fileserver

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FileService)
class FileServiceSpec extends Specification {

    def "return null when requested file outside base directory"() {
        given:
        def basePath = "test/integration/resources/dir"
        def filePath = "../test.txt"

        expect:
        service.loadFile(basePath, filePath) == null
    }

    def "return null when requested file doesn't exist"() {
        given:
        def basePath = "test/integration"
        def filePath = "nonExistingFile"

        expect:
        service.loadFile(basePath, filePath) == null
    }

    def "return existing file"() {
        given:
        def basePath = "test/integration/resources/"
        def filePath = "test.txt"
        def textFromFile = new File("$basePath/$filePath").text

        expect:
        service.loadFile(basePath, filePath).text == textFromFile
    }

    def "return null when path to directory is given"(){
        expect:
        service.loadFile(basePath, filePath) == null

        where:
        basePath << ["test/integration/", "test/integration/resources/"]
        filePath << ["resources/", ""]
    }
}