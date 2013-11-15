class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/httpservice/$action?/$key/$opt**?"(controller: 'HTTPService')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
