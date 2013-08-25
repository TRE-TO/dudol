package dudol

class Provedor {

	// Nome do exchange no RabbitMQ.
	String exchange

	static hasMany = [destinos: Destino]
	
    static constraints = {
    	exchange blank: false, nullable: false, unique: true
    }
}
