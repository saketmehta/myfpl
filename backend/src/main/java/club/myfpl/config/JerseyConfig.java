package club.myfpl.config;

import club.myfpl.repositories.ClubRepository;
import club.myfpl.resources.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * User: Saket
 * Date: 10/01/17
 * Time: 11:27 AM
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerFilters();
        registerEndpoints();
        registerInterceptors();
    }

    private void registerInterceptors() {
    }

    private void registerFilters() {
    }

    private void registerEndpoints() {
        register(UserResource.class);
        register(ClubResource.class);
        register(PlayerResource.class);
        register(FPLProxyResource.class);
    }

}
