package club.myfpl.security;

import club.myfpl.filters.JWTAuthFilter;
import club.myfpl.filters.JWTLoginFilter;
import club.myfpl.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * User: Saket
 * Date: 23/12/16
 * Time: 11:51 AM
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticatedUserService authenticatedUserService;
    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public SecurityConfig(AuthenticatedUserService authenticatedUserService, TokenAuthenticationService tokenAuthenticationService) {
        this.authenticatedUserService = authenticatedUserService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticatedUserService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
    }
}
