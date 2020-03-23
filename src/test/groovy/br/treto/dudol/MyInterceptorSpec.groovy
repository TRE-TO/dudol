package br.treto.dudol

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class MyInterceptorSpec extends Specification implements InterceptorUnitTest<MyInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test my interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"my")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
