package example.jaxrs;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/")
public class ValidateSampleResource {
    @GET
    @Path("/")
    public String index(@NotNull @QueryParam("name") String name) {
        return "Hello " + name;
    }

    @GET
    @Path("minmax")
    public String minmax(
            @NotNull @DecimalMax(value = "10") @DecimalMin(value = "3") @QueryParam("number") int number) {
        return Integer.toString(number * 10);
    }

    @GET
    @Path("pattern")
    public String pattern(
            @NotNull @Pattern(regexp = "a+b*c") @QueryParam("pattern") String pattern) {
        return "pattern = " + pattern;
    }

    @GET
    @Path("length")
    public String length(@NotNull @Length(min = 3, max = 10) @QueryParam("str") String str) {
        return str;
    }

    @GET
    @Path("email")
    public String email(@NotNull @Email @QueryParam("email") String email) {
        return email;
    }
}
