grails-file-server
==================

Grails plugin that allows you to serve files from any directory on disk.

Add download controller provided by this plugin to grails-app/conf/UrlMappings.groovy:
class UrlMappings {
    static mappings = {
        "/download/$root/$path**"(controller: "download")
    }
}

Add all directories you want to serve files from to grails-app/conf/Config.groovy:
grails.plugins.fileserver.paths=[
    "root1": "/path/to/dir1",
    "root2": "/path/to/dir2"
]
