package dudol

import java.util.List
import grails.converters.JSON

class DestinoService {

    def gerarParametros(String exchange, String chaveDestino = null) {
    	if (chaveDestino) {
    		Destino.executeQuery("""select d.parametros
    								from Destino as d
    								where d.provedor.exchange = :exchange and d.chave = :chaveDestino""",
    							 [exchange: exchange, chaveDestino: chaveDestino])
    	}
    	else {
    		Destino.executeQuery("select d.parametros from Destino as d where d.provedor.exchange = ?", [exchange])
    	}
    }
}
