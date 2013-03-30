package org.grails.plugins.fileserver

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(DownloadController)
class DownloadControllerSpec extends Specification {

    private static final PATHS = ["path1": "dir1/subdir1", "path2": "dir2/subdir2"]
    private static final CONTEXT = "/testcontext"
    private static final String FILE_PATH = "some/file/to/download.txt"

    private def setForwardURI(root, path){
        request.forwardURI = "${request.contextPath}/${controller.controllerName}/${root}/${path}"
    }

    def setup(){
        config.grails.plugins.fileserver.paths = PATHS
        request.contextPath = CONTEXT
    }

    def "404 when missing configuration"() {
        given:
        config.grails.plugins.fileserver.paths = null
        params.root = PATHS.entrySet().first().key
        setForwardURI(params.root, FILE_PATH)

        when:
        controller.index()

        then:
        response.status == 404
    }


    def "404 when root doesn't exist"() {
        given:
        params.root = "wrongpath"
        setForwardURI(params.root, FILE_PATH)

        when:
        controller.index()

        then:
        response.status == 404
    }

    def "404 when file not found"() {
        given:
        FileService fileServiceMock = Mock()
        controller.fileService = fileServiceMock
        params.root = PATHS.entrySet().first().key
        setForwardURI(params.root, FILE_PATH)

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

        params.root = PATHS.entrySet().first().key
        setForwardURI(params.root, FILE_PATH)

        when:
        controller.index()

        then:
        1 * fileServiceMock.loadFile(_, _) >> fileToReturn
        response.contentAsByteArray == bytes
    }

    def "parse forwardURI"(){
        given:
        FileService fileServiceMock = Mock()
        controller.fileService = fileServiceMock
        params.root = PATHS.entrySet().first().key
        setForwardURI(params.root, FILE_PATH)

        when:
        controller.index()

        then:
        1 * fileServiceMock.loadFile(PATHS.entrySet().first().value, FILE_PATH) >> null
    }
}