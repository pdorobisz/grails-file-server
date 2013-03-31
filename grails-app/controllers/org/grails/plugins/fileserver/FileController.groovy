package org.grails.plugins.fileserver

class FileController {

    def fileService

    def index() {
        def root = params.root
        def path = getPath(request, root)

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

    private String getPath(request, root){
        String uri = request.forwardURI
        String context = request.contextPath
        return uri.replaceFirst("^${context}/${controllerName}/${root}/", '')
    }
}
