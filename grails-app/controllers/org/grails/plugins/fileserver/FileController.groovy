package org.grails.plugins.fileserver

class FileController {

    def fileService

    def index() {
        def root = params.root
        def path = getPath(request.forwardURI, params.path)

        def basePath = grailsApplication.config.grails?.plugins?.fileserver?.paths?.get(root)
        File file = basePath ? fileService.loadFile(basePath, path) : null

        if (file) {
            log.debug("$root/$path, sending file: $file.absolutePath")
            response.outputStream << file.bytes
        } else {
            log.debug("$root/$path, file not found - dir: $basePath, file: $path")
            response.status = 404
        }
    }

    private String getPath(uri, path) {
        String last = uri.substring(uri.lastIndexOf('/') + 1)
        String path2 = path.substring(0, path.lastIndexOf('/') + 1)
        return path2 + last
    }
}
