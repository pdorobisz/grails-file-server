class UrlMappings {

	static mappings = {
		"500"(view:'/error')
        "/files/$root/$path**"(controller: "file")
	}
}
