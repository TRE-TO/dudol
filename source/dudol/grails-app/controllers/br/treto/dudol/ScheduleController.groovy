package br.treto.dudol



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ScheduleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String invertKey) {
        invert(invertKey)
        params.max = Math.min(max ?: 10, 100)
        def list = Schedule.list(params)
        def statusList = []
        for (def s in list) {
            statusList.add(ScheduleRefManager.isRunning(s.key))
        }
        respond list, model:[scheduleInstanceCount: Schedule.count(), statuses: statusList]
    }

    def show(Schedule scheduleInstance) {
        respond scheduleInstance
    }

    def invert(String key) {
        Schedule s = Schedule.findByKey(key)
        if (s != null) {
            if (ScheduleRefManager.isRunning(s)) {
                ScheduleRefManager.cancel(key)
            }
            else {
                ScheduleRefManager.start(s, 30)
            }
        }
    }

    def create() {
        respond new Schedule(params)
    }

    @Transactional
    def save(Schedule scheduleInstance) {
        if (scheduleInstance == null) {
            notFound()
            return
        }

        if (scheduleInstance.hasErrors()) {
            respond scheduleInstance.errors, view:'create'
            return
        }

        scheduleInstance.save flush:true

        ScheduleRefManager.start(scheduleInstance, 30)

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'scheduleInstance.label', default: 'Schedule'), scheduleInstance.id])
                redirect action: 'index'
            }
            '*' { respond scheduleInstance, [status: CREATED] }
        }
    }

    def edit(Schedule scheduleInstance) {
        respond scheduleInstance
    }

    @Transactional
    def update(Schedule scheduleInstance) {
        if (scheduleInstance == null) {
            notFound()
            return
        }

        if (scheduleInstance.hasErrors()) {
            respond scheduleInstance.errors, view:'edit'
            return
        }

        scheduleInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Schedule.label', default: 'Schedule'), scheduleInstance.id])
                redirect action: 'index'
            }
            '*'{ respond scheduleInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Schedule scheduleInstance) {

        if (scheduleInstance == null) {
            notFound()
            return
        }

        ScheduleRefManager.cancel(scheduleInstance.key)

        scheduleInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Schedule.label', default: 'Schedule'), scheduleInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduleInstance.label', default: 'Schedule'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
