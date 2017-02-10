package club.myfpl.resources;

import club.myfpl.fpl.FPLConstants;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * User: Saket
 * Date: 08/02/17
 * Time: 4:48 PM
 */
@Component
@Path("bootstrap")
@Produces("application/json")
public class BootstrapResource {

    @GET
    @Path("static")
    public Response getBootstrapStaticData() {
        return Response.ok(doAPICall(FPLConstants.BOOTSTRAP_STATIC_ENDPOINT)).build();
    }

    @GET
    @Path("dynamic")
    public Response getBootstrapDynamicData() {
        return Response.ok(doAPICall(FPLConstants.BOOTSTRAP_DYNAMIC_ENDPOINT)).build();
    }

    private String doAPICall(String relativePath) {
        String absoluteURL = FPLConstants.BASE_URL + relativePath;
        try {
            URL url = new URL(absoluteURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while request at URL: " + absoluteURL, e);
        }
    }
}
