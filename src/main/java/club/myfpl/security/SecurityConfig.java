package club.myfpl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Saket
 * Date: 23/12/16
 * Time: 11:51 AM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public SecurityConfig(AuthenticatedUserService authenticatedUserService) {
        this.authenticatedUserService = authenticatedUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticatedUserService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/rest/csrf").permitAll().anyRequest().authenticated();
    }

    @RestController
    public class CsrfController {
        @RequestMapping("csrf")
        public CsrfToken csrf(CsrfToken token) {
            return token;
        }
    }
}
