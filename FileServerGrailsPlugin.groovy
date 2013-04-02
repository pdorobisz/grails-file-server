class FileServerGrailsPlugin {
    def version = "0.2"
    def grailsVersion = "2.0 > *"
    def title = "File Server Plugin"
    def author = "Piotr Dorobisz"
    def authorEmail = "pdorobisz@gmail.com"
    def description = '''\
This plugin provides controller that allows you to serve files from any directory on disk. You can specify multiple
"root" directories which contain files that should be served. In request you have to specify root directory you want to
get file from and path to file (relative to root).
'''

    def documentation = "https://github.com/pdorobisz/grails-file-server/wiki"
    def license = "APACHE"
    def issueManagement = [system: "GitHub", url: "https://github.com/pdorobisz/grails-file-server/issues"]
    def scm = [url: "https://github.com/pdorobisz/grails-file-server"]
}
