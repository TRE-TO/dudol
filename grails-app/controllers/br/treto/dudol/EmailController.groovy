package br.treto.dudol

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailException
import org.apache.commons.mail.HtmlEmail
import org.apache.commons.mail.MultiPartEmail
import org.apache.commons.mail.SimpleEmail
import javax.mail.MessagingException
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress

import static grails.async.Promises.task

class EmailController {

	static allowedMethods = [enviar: "POST"]
	
	def enviar() {
        task {
            String missing = validate(params).join(', ')
            if (missing) {
                render(status: 400, text: "Os seguintes parâmetros são obrigatórios: $missing")
                return
            }

            def toList = [params.to].flatten()
            def ccList = [params.cc].flatten()
            def bccList = [params.bcc].flatten()

            if (!isValid(params?.from)
                    || toList.find { !isValid(it) }
                    || ccList.find { it && !isValid(it) }
                    || bccList.find { it && !isValid(it) }) {
                render(status: 400, text: 'Um ou mais endereços de email são inválidos.')
                log.warn "Um ou mais endereços de email inválidos. De ${params?.from}, para ${params?.to}."
                return
            }

            org.apache.commons.mail.Email email = mountEmail(toList, ccList, bccList)

            try {
                sendEmail(email, false)
                log.info "Email enviado com sucesso. De ${params.from}, para ${params.to}."
            }
            catch (AddressException ex) {
                render(status: 400, text: "Email inválido, de ${params.from}, para ${params.to}: ${ex.getMessage()}")
                log.error "Email inválido. De ${params.from}, para ${params.to}. Servidor ${email.hostName}:${email.smtpPort}."
                return
            }
            catch (MessagingException ex) {
                render(status: 400, text: "Não foi possível conectar ao servidor. De ${params.from}, para ${params.to}: ${ex.getMessage()}")
                log.error "Não foi possível conectar ao servidor. Será realizada uma nova tentativa. De ${params.from}, para ${params.to}. Servidor ${email.hostName}:${email.smtpPort}."
                tryResend(email)
                return
            }
            catch (EmailException ex) {
                tryResend(email)
                render(status: 400, text: 'Ocorreu um problema ao enviar o email: ' + ex.getMessage())
                log.error "Ocorreu um erro ao enviar o email: ${ex.getMessage()}. De ${params.from}, para ${params.to}. Servidor ${email.hostName}:${email.smtpPort}."
                return
            }
            render(status: 200, text: 'Email enviado.')
        }
	}

    private org.apache.commons.mail.Email mountEmail(List<String> toList, List<String> ccList, List<String> bccList) {
        org.apache.commons.mail.Email email
        if (!params.html || params.html.equals(0)) email = new SimpleEmail().setMsg(params.message)
        else {
            email = new HtmlEmail().setHtmlMsg(params.message)
            email.updateContentType('text/html')
        }

        def conf = Email.get(1)

        email.setHostName(conf.host)
        email.setSmtpPort(conf.port)
        email.setSSLOnConnect(conf.ssl)
        email.setFrom(params.from, params.fromname)
        email.setSubject(params.subject)
        if (conf.username) {
            email.setAuthenticator(new DefaultAuthenticator(conf.username, conf.password))
        }
        toList.each { email.addTo(it) }
        ccList.each { if (it) email.addCc(it) }
        bccList.each { if (it) email.addBcc(it) }
        email
    }

    private List<String> validate(params) {
		def obrigatorios = ['from', 'fromname', 'to', 'subject', 'message']
		obrigatorios.findAll { params[it] == null }
	}

	public Boolean isValid(String email) {
	    try {
			new InternetAddress(email).validate()
			true
	    }
	    catch (AddressException e) {
	    	false
	    }
	}

    private void tryResend(org.apache.commons.mail.Email email) {
        new Thread() {
            public void run() {
                Thread.sleep(180 * 1000)
                try {
                    sendEmail(email, true)
                }
                catch (Exception ex) {
                    log.error "Nova tentativa de conectar ao servidor também falhou. De ${email.fromAddress}, para ${email.toAddresses[0]}. Servidor ${email.hostName}:${email.smtpPort}."
                }
            }
        }.start()
    }

    private void sendEmail(org.apache.commons.mail.Email email, Boolean alreadySent) {
        if (email instanceof MultiPartEmail && alreadySent) {
            email.sendMimeMessage()
        }
        else {
            email.send()
        }
    }
}