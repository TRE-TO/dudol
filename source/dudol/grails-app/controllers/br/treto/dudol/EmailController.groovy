package br.treto.dudol

import org.apache.commons.mail.HtmlEmail
import org.apache.commons.mail.SimpleEmail
import org.apache.commons.mail.DefaultAuthenticator
import javax.mail.internet.InternetAddress
import javax.mail.internet.AddressException

class EmailController {

	static allowedMethods = [enviar: "POST"]
	
	def enviar() {
		String ausentes = validar(params).join(', ')
		if (ausentes) {
			render(status: 400, text: "Os seguintes parâmetros são obrigatórios: $ausentes")
			return
		}

		org.apache.commons.mail.Email email
		if (!params.html || params.html.equals(0)) email = new SimpleEmail().setMsg(params.message)
		else email = new HtmlEmail().setHtmlMsg(params.message)

		def toList = [params.to].flatten()
		def ccList = [params.cc].flatten()
		def bccList = [params.bcc].flatten()

		if (!isValido(params.from)
			|| toList.find{ !isValido(it) }
			|| ccList.find{ it && !isValido(it) }
			|| bccList.find{ it && !isValido(it) }) {
			render(status: 400, text: 'Um ou mais endereços de email são inválidos.')
			return
		}

		def conf = Email.get(1)

		email.setHostName(conf.host)
		email.setSmtpPort(conf.port)
		if (conf.username) {
			email.setAuthenticator(new DefaultAuthenticator(conf.username, conf.password))
		}
		email.setSSLOnConnect(conf.ssl)
		email.setFrom(params.from, params.fromname)
		email.setSubject(params.subject)
		
		toList.each{ email.addTo(it) }
		ccList.each{ if (it) email.addCc(it) }
		bccList.each{ if (it) email.addBcc(it) }

		email.send()
	}

	private List<String> validar(params) {
		def obrigatorios = ['from', 'fromname', 'to', 'subject', 'message']
		obrigatorios.findAll { params[it] == null }
	}

	public Boolean isValido(String email) {
	    try {
			new InternetAddress(email).validate()
			true
	    }
	    catch (AddressException e) {
	    	false
	    }
	}
}