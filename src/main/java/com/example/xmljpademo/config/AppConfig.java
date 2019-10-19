package com.example.xmljpademo.config;

import com.example.xmljpademo.MyContextListener;
import com.example.xmljpademo.filters.AsyncRequestLoggingFilter;
import com.example.xmljpademo.filters.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class AppConfig extends AbstractHttpSessionApplicationInitializer {

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/employees/*", "/scopes/*");
        //registrationBean.setDispatcherTypes(DispatcherType.ASYNC);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AsyncRequestLoggingFilter> asyncFilter() {
        FilterRegistrationBean<AsyncRequestLoggingFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new AsyncRequestLoggingFilter());
        registrationBean.addUrlPatterns("/ruleTheWorld");
        //registrationBean.setDispatcherTypes(DispatcherType.ASYNC);
        return registrationBean;
    }

    @Bean
    public MyContextListener myContextListener(){
        return new MyContextListener();
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID111"); // <1>
        serializer.setCookiePath("/"); // <2>
        //serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); // <3>
        return serializer;
    }
}
