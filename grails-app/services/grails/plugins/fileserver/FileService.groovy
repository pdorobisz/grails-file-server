package grails.plugins.fileserver

import java.nio.file.Paths

class FileService {

    def transactional = false

    def loadFile(basePath, filePath) {
        def pathToBase = Paths.get(basePath).normalize()
        def pathToFile = Paths.get(basePath, filePath).normalize()

        if (pathToFile.startsWith(pathToBase)) {
            def file = pathToFile.toFile()
            if (file.exists()) {
                return file
            }
        }
        return null
    }
}
