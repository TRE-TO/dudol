package br.treto.dudol



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ScheduleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Schedule.list(params), model:[scheduleInstanceCount: Schedule.count()]
    }

    def show(Schedule scheduleInstance) {
        respond scheduleInstance
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

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'scheduleInstance.label', default: 'Schedule'), scheduleInstance.id])
                redirect scheduleInstance
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
                redirect scheduleInstance
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
