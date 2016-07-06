package example.jaxrs

import groovy.json.JsonSlurper
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.core.Application

class JsonResourceTest extends Specification {
    @Shared
    def jerseyTest = new JerseyTest() {
        @Override
        protected Application configure() {
            return new ResourceConfig(JsonResource.class)
        }
    }

    def setupSpec() {
        jerseyTest.setUp()
    }

    def cleanupSpec() {
        jerseyTest.tearDown()
    }

    def "test index"() {
        when:
        def response = jerseyTest.target("/").request().get(String.class)
        then:
        response == "Hello World"
    }

    def "test list"() {
        when:
        def jsonslurper = new JsonSlurper()
        def response = jerseyTest.target("/list").request().get(String.class)
        then:
        jsonslurper.parseText(response) == jsonslurper.parseText('''["a", "b"]''')

    }
}
