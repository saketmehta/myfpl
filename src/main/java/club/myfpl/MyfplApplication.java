package club.myfpl;

import club.myfpl.auth.AuthFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class MyfplApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyfplApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean authFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/rest/*"));
        return filterRegistrationBean;
    }
}
