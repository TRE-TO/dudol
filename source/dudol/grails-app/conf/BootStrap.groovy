class BootStrap {

    def init = { servletContext ->
    	def padlog = new dudol.Destino(chave: "padlog", parametros: "{url: 'http://url/PadLog'}")
    	def recursos = new dudol.Destino(chave: "recursos", parametros: "{url: 'http://url/Recursos'}")
    	new dudol.Provedor(exchange: "ex1")
    		.addToDestinos(padlog)
    		.addToDestinos(recursos)
    		.save()
    }
    def destroy = {
    }
}
