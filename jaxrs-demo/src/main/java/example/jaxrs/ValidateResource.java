package example.jaxrs;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/")
public class ValidateResource {
    @GET
    public String index(@NotNull @QueryParam("name") String name) {
        return "Hello " + name;
    }

}
