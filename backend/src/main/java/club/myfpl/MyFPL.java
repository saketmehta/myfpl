package club.myfpl;

import club.myfpl.auth.AuthFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ServletComponentScan
@SpringBootApplication
@Controller
public class MyFPL {

    public static void main(String[] args) {
        SpringApplication.run(MyFPL.class, args);
    }

    @RequestMapping(value = {"/home/**", "/settings", "/signup", "/my-team/**", "/leagues/**"}, method = RequestMethod.GET)
    public String indexPage() {
        return "forward:/index.html";
    }

    @Bean
    public FilterRegistrationBean authFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/rest/*"));
        return filterRegistrationBean;
    }
}
