class UrlMappings {

	static mappings = {
//		"/$controller/$action?/$id?"{
//			constraints {
//				// apply constraints here
//			}
//		}
//
//		"/"(view:"/index")
		"500"(view:'/error')
        "/download/$root/$path**"(controller: "download", action: "index")
	}
}
