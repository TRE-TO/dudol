package br.treto.dudol

class TesteController {

	def get() {
        ScheduleRefManager.cancel('script1')
        render 'parou...'
	}
}