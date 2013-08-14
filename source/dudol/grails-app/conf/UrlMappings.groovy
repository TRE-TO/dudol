class UrlMappings {

	static mappings = {
		"/$controller/$grupo"(action: "receber")

//		"/$controller/$action?/$id?"{
//			constraints {
//				// apply constraints here
//			}
//		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
