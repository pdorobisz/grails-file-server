package grails.plugins.fileserver

class DownloadController {

    def fileService

    def index() {
        def root = params.root
        def path = params.path

        def basePath = grailsApplication.config.grails?.plugins?.fileserver?.paths?.get(root)
        File file = basePath ? fileService.loadFile(basePath, path) : null

        if (file) {
            log.debug("sending file $root/$path: $file.absolutePath")
            response.outputStream << file.bytes
        } else {
            log.debug("file not found: $root/$path (dir: $basePath, file: $path)")
            response.status = 404
        }
    }
}
