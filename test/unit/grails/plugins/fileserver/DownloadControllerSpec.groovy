package grails.plugins.fileserver

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(DownloadController)
class DownloadControllerSpec extends Specification {

    private static final PATHS = ["path1": "dir1/subdir1", "path2": "dir2/subdir2"]

    def "404 when missing configuration"() {
        given:
        params.root = "somepath"
        params.path = "some/file/to/download.txt"

        when:
        controller.index()

        then:
        response.status == 404
    }


    def "404 when root doesn't exist"() {
        given:
        config.grails.plugins.fileserver.paths = PATHS
        params.root = "somepath"
        params.path = "some/file/to/download.txt"

        when:
        controller.index()

        then:
        response.status == 404
    }

    def "404 when file not found"() {
        given:
        FileService fileServiceMock = Mock()
        controller.fileService = fileServiceMock

        config.grails.plugins.fileserver.paths = PATHS
        params.root = PATHS.entrySet().first().key
        params.path = "some/file/to/download.txt"

        when:
        controller.index()

        then:
        1 * fileServiceMock.loadFile(_, _) >> null
        response.status == 404
    }

    def "return file content"() {
        given:
        FileService fileServiceMock = Mock()
        controller.fileService = fileServiceMock
        def bytes = [65, 66, 67, 97, 98, 99] as byte[]
        def fileToReturn = new File("") {
            public byte[] getBytes() {
                return bytes
            }
        }

        config.grails.plugins.fileserver.paths = PATHS
        params.root = PATHS.entrySet().first().key
        params.path = "some/file/to/download.txt"

        when:
        controller.index()

        then:
        1 * fileServiceMock.loadFile(_, _) >> fileToReturn
        response.contentAsByteArray == bytes
    }
}