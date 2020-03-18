package br.treto.dudol

//import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)
class EmailAdminController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def email = Email.list()[0] ?: new Email(id: 1)
        render (view: "edit", model: [emailInstance: email])
    }

 //   @Transactional
    def update(Email emailInstance) {
        if (emailInstance == null) {
            notFound()
            return
        }

        if (emailInstance.hasErrors()) {
            respond emailInstance.errors, view:'edit'
            return
        }

        emailInstance.save flush:true

        request.withFormat {
            form {
                flash.message = "Configurações atualizadas"
                redirect action: "index", method: "GET"
            }
            '*'{ respond emailInstance, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'emailInstance.label', default: 'Email'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
