import br.treto.dudol.*

class BootStrap {

    def init = { servletContext ->
    	new Email(host: "smtp.gmail.com", port: 485, username: "michaelss@gmail.com", password: "erpexeolvofwtbpg", ssl: true).save()
    	new HTTPService(key: 'tre', baseUrl: 'http://www.tre-to.jus.br/').save(flush: true)

    	scheduleFromDB()
    }
    def destroy = {
    }

    def scheduleFromDB() {
    	for (s in Schedule.findAll()) {
            ScheduleRefManager.start(s)
    	}
    }
}
