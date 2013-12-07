package br.treto.dudol

class HTTPService {

	String key
	String baseUrl

	static constraints = {
		baseUrl url: true
	}

	static mapping = {
		version false
	}

	def setBaseUrl(String url) {
		baseUrl = (url.endsWith('/')) ? url.substring(0, url.length() - 1) : url
	}

}
