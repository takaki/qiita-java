package example.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class JsonResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String index() {
        return "Hello World";
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> list() {
        return Arrays.asList("a","b");
    }
}
