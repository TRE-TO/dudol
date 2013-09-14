class BootStrap {

    def init = { servletContext ->
    	def padlog = new dudol.Destino(chave: "padlog", parametros: "url=http://localhost:8090/teste/pessoa")
    	def recursos = new dudol.Destino(chave: "recursos", parametros: "url=http://api.hostip.info/country.php")
    	new dudol.Provedor(exchange: "ex1")
    		.addToDestinos(padlog)
    		.addToDestinos(recursos)
    		.save()
    }
    def destroy = {
    }
}
