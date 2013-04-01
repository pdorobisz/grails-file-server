package org.grails.plugins.fileserver

import java.nio.file.Paths

class FileService {

    static transactional = false

    File loadFile(String basePath, String filePath) {
        def pathToBase = Paths.get(basePath).normalize()
        def pathToFile = Paths.get(basePath, filePath).normalize()

        if (pathToFile.startsWith(pathToBase)) {
            def file = pathToFile.toFile()
            if (file.exists() && file.isFile()) {
                return file
            }
        }
        return null
    }
}
