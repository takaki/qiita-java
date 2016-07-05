package example.jaxrs

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.ServerProperties
import org.glassfish.jersey.test.JerseyTest
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.core.Application

class ValidateSampleResourceTest extends Specification {
    @Shared
    def jerseyTest = new JerseyTest() {
        @Override
        protected Application configure() {
            return new ResourceConfig(ValidateSampleResource.class).
                    property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true)
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
        def response = jerseyTest.target("/").queryParam("name", "World").request().get()

        then:
        response.getStatus() == 200
        response.readEntity(String.class) == "Hello World"
    }

    def "test minmax"() {
        when:
        def response = jerseyTest.target("/minmax").queryParam("number", "5").request().get()

        then:
        response.getStatus() == 200
        response.readEntity(String.class) == "50"

        expect:
        jerseyTest.target("/minmax").request().get().getStatus() != 200
        jerseyTest.target("/minmax").queryParam("number", "20").request().get().getStatus() != 200
        jerseyTest.target("/minmax").queryParam("number", "1").request().get().getStatus() != 200
    }

    def "test pattern" () {
        expect:
        jerseyTest.target("/pattern").queryParam("pattern", "abc").request().get().getStatus() == 200
        jerseyTest.target("/pattern").queryParam("pattern", "aac").request().get().getStatus() == 200

        jerseyTest.target("/pattern").queryParam("pattern", "xxx").request().get().getStatus() != 200
        jerseyTest.target("/pattern").queryParam("pattern", "xabc").request().get().getStatus() != 200
    }

    def "test length" () {
        expect:
        jerseyTest.target("/length").queryParam("str", "1234567").request().get().getStatus() == 200
        jerseyTest.target("/length").queryParam("str", "123").request().get().getStatus() == 200

        jerseyTest.target("/length").queryParam("str", "abcdefghijk").request().get().getStatus() != 200
        jerseyTest.target("/length").queryParam("str", "aa").request().get().getStatus() != 200
    }

    def "test email" () {
        expect:
        jerseyTest.target("/email").queryParam("email", "test@example.org").request().get().getStatus() == 200
        jerseyTest.target("/email").queryParam("email", "test@localhost").request().get().getStatus() == 200

        jerseyTest.target("/email").queryParam("email", "test").request().get().getStatus() != 200
    }

}
