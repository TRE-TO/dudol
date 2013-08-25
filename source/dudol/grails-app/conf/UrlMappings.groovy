class UrlMappings {

	static mappings = {
		"/$controller/$exchange/$destino?"(action: "enviar")

//		"/$controller/$action?/$id?"{
//			constraints {
//				// apply constraints here
//			}
//		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
