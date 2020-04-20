package br.treto.dudol

import grails.gorm.transactions.Transactional
import org.hibernate.criterion.Restrictions
import org.hibernate.sql.JoinType

import java.text.SimpleDateFormat

//import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)
class EmailAdminController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {

        String dia = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        def now = new SimpleDateFormat("dd/MM/yyyy").parse(dia);

        def emails =  Email.createCriteria().list{
            createAlias('envios', 'e', JoinType.LEFT_OUTER_JOIN, Restrictions. eq('e.data',now))

            order('ordem','asc')

        }
      //  println "emails---:"+emails
       // def emails = Email.findAll([sort:"ordem"]);
        render (view: "index", model: [lista: emails])
    }
    def edit(Integer id){

        def email = Email.get(id)
        if(request.method == 'GET'){

            render (view:'edit', model:['emailInstance':email])
        }
    }
    @Transactional
    def create(){

        if(request.method == 'GET'){
            def email= new Email()
            render (view:'create', model:['emailInstance':email])
        }
        else{
            def e = Email.findAll([sort:"ordem", order:"desc", limit:1]);
            Integer ordem=1;
            if(e.size() >0 && e[0] !=null)
                ordem = e[0].ordem+1;
            Email email = new Email()
            email.host = params.host
            email.port = params.port.toInteger()
            email.username = params.username
            email.password = params.password
            email.ordem = ordem
            email.qtdeMaxima = params.qtdeMaxima.toInteger()
            email.ssl = params.ssl ? true : false

            if (email.hasErrors()) {
                respond emailInstance.errors, view:'create'
                return
            }
            email.save flush:true
            request.withFormat {
                form {
                    flash.message = "Email adicionado"
                    redirect action: "index", method: "GET"
                }
                '*'{ respond emailInstance, [status: OK] }
            }

        }

    }
    @Transactional
    def excluir(Integer id){
        def email = Email.get(id)
        email.delete(flush:true)
        flash.message = "Dados excluídos"
        redirect action: "index", method: "GET"

    }
    @Transactional
    def up(Integer id){
        def email = Email.get(id)
        def obj = Email.find("from Email as e where e.ordem < :ord order by  e.ordem desc",[ord:email.ordem])
        if(obj!=null && obj.instanceOf(Email)){
            Integer ord = email.ordem
            email.ordem = obj.ordem
            obj.ordem = ord
            email.save flush:true
            obj.save flush:true
        }

        redirect action: "index", method: "GET"

    }
    @Transactional
    def down(Integer id){
        def email = Email.get(id)

        def obj = Email.find("from Email as e where e.ordem > :ord order by  e.ordem desc",[ord:email.ordem])
        if(obj!=null && obj.instanceOf(Email)){
            Integer ord = email.ordem
            email.ordem = obj.ordem
            obj.ordem = ord
            email.save flush:true
            obj.save flush:true
        }


        redirect action: "index", method: "GET"

    }

    @Transactional
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
