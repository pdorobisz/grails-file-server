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
            // inspired by answer of tim_yates of http://stackoverflow.com/a/15379941/1323837
            // using this implementation, which does not load the file into memory
            // important for big files and/or servers with very limited memory
            file.withInputStream {
                response.outputStream << it
            }
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
