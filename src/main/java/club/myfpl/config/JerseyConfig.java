package club.myfpl.config;

import club.myfpl.auth.AuthEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * User: Saket
 * Date: 10/01/17
 * Time: 11:27 AM
 */
@Component
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerFilters();
        registerEndpoints();
    }

    private void registerFilters() {
    }

    private void registerEndpoints() {
        register(AuthEndpoint.class);
    }
}
