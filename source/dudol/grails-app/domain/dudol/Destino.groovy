package dudol

class Destino {

	// String que identifica um destino, que pode ser um sistema. Ex: PadLog.
	String chave

	// Parâmetros a serem usados pelos consumidores, em formato JSON.
	String parametros

	static belongsTo = [provedor: Provedor]

    static constraints = {
    	chave blank: false, nullable: false
    }

    static mapping = {
    	parametros sqlType: 'varchar', length: 2000
    }
}