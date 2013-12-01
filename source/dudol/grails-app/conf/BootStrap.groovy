import br.treto.dudol.*
import java.util.concurrent.*

class BootStrap {

    def init = { servletContext ->
    	new Email(host: "smtp.gmail.com", port: 485, username: "michaelss@gmail.com", password: "erpexeolvofwtbpg", ssl: true).save()
    	new HTTPService(key: 'tre', baseUrl: 'http://www.tre-to.jus.br/').save()
    	new Schedule(key: 'script1', executable: 'sh /home/michael/script.py', rateInSeconds: 1).save(flush: true)

    	scheduleFromDB()
    }
    def destroy = {
    }

    def scheduleFromDB() {
    	println '[DUDOL] Agendando tarefas para início em 60 segundos.....'

    	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5)
    	
    	for (s in Schedule.findAll()) {
			ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
				public void run() {
					Runtime.getRuntime().exec(s.executable)
        		}
    		},
    		30L,
    		new Long(s.rateInSeconds),
    		TimeUnit.SECONDS)

            ScheduleRefManager.add(s.key, scheduledFuture)
    	}
    }
}
