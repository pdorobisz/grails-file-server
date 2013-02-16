package grails.plugins.fileserver

class DownloadController {

    def fileService

    def index() {
        def root = params.root
        def path = params.path

        def basePath = grailsApplication.config.fileserver?.paths?.get(root)
        println "index: $root, $path"
        File file = basePath ? fileService.loadFile(basePath, path) : null

        if (file) {
            response.outputStream << file.bytes
        } else {
            response.status = 404
        }
    }
}
