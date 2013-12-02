package br.treto.dudol



import grails.test.mixin.*
import spock.lang.*

@TestFor(ScheduleController)
@Mock(Schedule)
class ScheduleControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.scheduleInstanceList
            model.scheduleInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.scheduleInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def schedule = new Schedule()
            schedule.validate()
            controller.save(schedule)

        then:"The create view is rendered again with the correct model"
            model.scheduleInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            schedule = new Schedule(params)

            controller.save(schedule)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/schedule/show/1'
            controller.flash.message != null
            Schedule.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def schedule = new Schedule(params)
            controller.show(schedule)

        then:"A model is populated containing the domain instance"
            model.scheduleInstance == schedule
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def schedule = new Schedule(params)
            controller.edit(schedule)

        then:"A model is populated containing the domain instance"
            model.scheduleInstance == schedule
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/schedule/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def schedule = new Schedule()
            schedule.validate()
            controller.update(schedule)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.scheduleInstance == schedule

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            schedule = new Schedule(params).save(flush: true)
            controller.update(schedule)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/schedule/show/$schedule.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/schedule/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def schedule = new Schedule(params).save(flush: true)

        then:"It exists"
            Schedule.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(schedule)

        then:"The instance is deleted"
            Schedule.count() == 0
            response.redirectedUrl == '/schedule/index'
            flash.message != null
    }
}
