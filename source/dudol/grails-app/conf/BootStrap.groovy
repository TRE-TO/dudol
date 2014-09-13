import br.treto.dudol.*

class BootStrap {

    def init = { servletContext ->
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
