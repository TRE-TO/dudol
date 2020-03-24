package br.treto.dudol

class Schedule {

	String key
	String executable
	Integer rateInSeconds

	static mapping = {
		rateInSeconds column: "rate_in_seconds"
	}
    static constraints = {
    }
}
