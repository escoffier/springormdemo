package com.example.xmljpademo.config;

import com.example.xmljpademo.model.scopes.HelloMessageGenerator;
import com.example.xmljpademo.model.scopes.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

@Configuration
public class ScopeConfig {
    final static Logger logger = LoggerFactory.getLogger(ScopeConfig.class);
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator requestScopedBean() {
        logger.info("generating SCOPE_REQUEST bean");
        return new HelloMessageGenerator(Thread.currentThread().getName());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator sessionScopedBean() {
        logger.info("generating SCOPE_SESSION bean");
        return new HelloMessageGenerator(Thread.currentThread().getName());
    }

    @Bean
    @Scope("prototype")
    public Person personPrototype() {
        logger.info("generating prototype bean");
        Random random = new Random();
        return new Person("robbie-" + random.nextInt());
    }
}
