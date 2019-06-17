package com.example.xmljpademo.config;

import com.example.xmljpademo.MyContextListener;
import com.example.xmljpademo.filters.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/employees/*");
        return registrationBean;
    }

    @Bean
    public MyContextListener myContextListener(){
        return new MyContextListener();
    }
}
