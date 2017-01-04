package club.myfpl.security.config;

import club.myfpl.security.filters.AuthFilter;
import club.myfpl.security.filters.LoginFilter;
import club.myfpl.security.services.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * User: Saket
 * Date: 23/12/16
 * Time: 11:51 AM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_ANT_PATTERN = "/login";
    private static final String ADMIN_ANT_PATTERN = "/admin/**";

    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public SecurityConfig(AuthenticatedUserService authenticatedUserService) throws Exception {
        this.authenticatedUserService = authenticatedUserService;
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManager());
        return loginFilter;
    }

    @Bean
    public AuthFilter authFilter() throws Exception {
        return new AuthFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticatedUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl().disable();
        http.exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("'Bearer token_type=\"JWT\"'"));
        http.authorizeRequests()
                .antMatchers(LOGIN_ANT_PATTERN).permitAll()
                .antMatchers(ADMIN_ANT_PATTERN).hasAuthority("ADMIN")
                .antMatchers("/**").hasAuthority("USER")
                .anyRequest().authenticated();
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
