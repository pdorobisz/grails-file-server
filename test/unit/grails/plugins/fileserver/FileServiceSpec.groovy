package grails.plugins.fileserver

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FileService)
class FileServiceSpec extends Specification {

    def "return null when requested file outside base directory"() {
        given:
        def basePath = "/path/to/some/dir"
        def filePath = "dir2/../../file.txt"

        expect:
        service.loadFile(basePath, filePath) == null
    }

    def "return null when requested file doesn't exist"() {
        given:
        def basePath = new File('test/').absolutePath
        def filePath = "notExistingFile"

        expect:
        service.loadFile(basePath, filePath) == null
    }

    def "return existing file"() {
        given:
        def basePath = new File('test/integration/resources/').absolutePath
        def filePath = "test.txt"
        def textFromFile = new File("$basePath/$filePath").text

        expect:
        service.loadFile(basePath, filePath).text == textFromFile
    }
}