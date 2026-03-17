package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpelApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean<SpELInjectionFilter> spELInjectionFilter() {
        FilterRegistrationBean<SpELInjectionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SpELInjectionFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
