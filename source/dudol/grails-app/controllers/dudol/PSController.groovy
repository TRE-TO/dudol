package dudol

import grails.converters.JSON

class PSController {

    def receber() {
	render params.grupo
//	render params as JSON
   }
}
