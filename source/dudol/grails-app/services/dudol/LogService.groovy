package dudol

class LogService {

    def registrarLog(String exchange, String ip) {
    	Thread.start {
	    	Log.withTransaction { 
	    		new Log(exchange: exchange, ip: ip).save()
	    	}
    	}
    }
}
