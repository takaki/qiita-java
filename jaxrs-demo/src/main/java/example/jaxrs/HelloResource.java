package example.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloResource {
    @GET
    public String index() {
        return "Hello World";
    }
}
