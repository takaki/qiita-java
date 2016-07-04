package example.jaxrs

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.core.Application

class HelloResourceTest extends Specification {
    @Shared
    def jerseyTest = new JerseyTest() {
        @Override
        protected Application configure() {
            return new ResourceConfig(HelloResource.class)
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

    def "test index 2"() {
        when:
        def response = jerseyTest.target("/").request().get()

        then:
        response.getStatus() == 200
        response.readEntity(String.class) == "Hello World"
    }

}
