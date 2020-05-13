package br.treto.dudol

class Envio {


	Date data
	Integer quantidade
	Email email


	static belongsTo = [email: Email]

}
