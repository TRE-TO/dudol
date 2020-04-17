package br.treto.dudol

class Email {


	String host
	Integer port
	String username
	String password
	Boolean ssl
	Integer ordem
	Integer qtdeMaxima

    static constraints = {
    	username nullable: true
    	password nullable: true
    }

}
