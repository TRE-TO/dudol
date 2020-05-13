package br.treto.dudol

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

class ScheduleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String invertKey) {
        params.max = Math.min(max ?: 10, 100)
        def list = Schedule.list(params)
        def statusList = []
        for (def s in list) {
            statusList.add(ScheduleRefManager.isRunning(s.key))
        }
        render view:'index', model:[scheduleInstanceList:list, scheduleInstanceCount: Schedule.count(), statuses: statusList]
    }

    def show(Integer id) {
        Schedule scheduleInstance = Schedule.findById(id)
        render (view: "show", model:[scheduleInstance: scheduleInstance])
    }

    def invert(String key) {
        Schedule s = Schedule.findByKey(key)
        if (s != null) {
            if (ScheduleRefManager.isRunning(s.key)) {
                ScheduleRefManager.cancel(key)
            }
            else {
                ScheduleRefManager.start(s)
            }
        }
        redirect action:"index"
    }

    def create() {
        Schedule scheduleInstance = new Schedule(params)
        render (view: "create", model:[scheduleInstance: scheduleInstance])
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

    def edit(Integer id) {
        Schedule scheduleInstance = Schedule.findById(id)
        render (view: "edit", model:[scheduleInstance: scheduleInstance])
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
