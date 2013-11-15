package br.treto.dudol



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HTTPServiceAdminController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HTTPService.list(params), model:[HTTPServiceInstanceCount: HTTPService.count()]
    }

    def show(HTTPService HTTPServiceInstance) {
        respond HTTPServiceInstance
    }

    def create() {
        respond new HTTPService(params)
    }

    @Transactional
    def save(HTTPService HTTPServiceInstance) {
        if (HTTPServiceInstance == null) {
            notFound()
            return
        }

        if (HTTPServiceInstance.hasErrors()) {
            respond HTTPServiceInstance.errors, view:'create'
            return
        }

        HTTPServiceInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'HTTPServiceInstance.label', default: 'HTTPService'), HTTPServiceInstance.id])
                redirect HTTPServiceInstance
            }
            '*' { respond HTTPServiceInstance, [status: CREATED] }
        }
    }

    def edit(HTTPService HTTPServiceInstance) {
        respond HTTPServiceInstance
    }

    @Transactional
    def update(HTTPService HTTPServiceInstance) {
        if (HTTPServiceInstance == null) {
            notFound()
            return
        }

        if (HTTPServiceInstance.hasErrors()) {
            respond HTTPServiceInstance.errors, view:'edit'
            return
        }

        HTTPServiceInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'HTTPService.label', default: 'HTTPService'), HTTPServiceInstance.id])
                redirect HTTPServiceInstance
            }
            '*'{ respond HTTPServiceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(HTTPService HTTPServiceInstance) {

        if (HTTPServiceInstance == null) {
            notFound()
            return
        }

        HTTPServiceInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'HTTPService.label', default: 'HTTPService'), HTTPServiceInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'HTTPServiceInstance.label', default: 'HTTPService'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
