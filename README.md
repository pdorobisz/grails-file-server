grails-file-server
==================

Grails 2.x plugin that allows you to serve files from any directory on disk.

Add dependency to _grails-app/conf/BuildConfig.groovy_:

    grails.project.dependency.resolution = {
        ...
        plugins {
            ...
            runtime ':file-server:0.2.1'
        }
    }

Add _download_ controller provided by this plugin to _grails-app/conf/UrlMappings.groovy_:

    class UrlMappings {
        static mappings = {
            "/download/$root/$path**"(controller: "download")
        }
    }

Add all directories you want to serve files from to _grails-app/conf/Config.groovy_:

    grails.plugins.fileserver.paths=[
        "root1": "/path/to/dir1",
        "root2": "/path/to/dir2"
    ]

For more information check plugins's documentation: https://github.com/pdorobisz/grails-file-server/wiki
