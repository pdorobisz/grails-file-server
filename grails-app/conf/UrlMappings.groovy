class UrlMappings {

	static mappings = {
		"500"(view:'/error')
        "/download/$root/**"(controller: "download", action: "index")
	}
}
