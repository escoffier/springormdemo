package com.example.xmljpademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class JpaDemoApplication {
    final static Logger logger = LoggerFactory.getLogger(JpaDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JpaDemoApplication.class);
        app.addListeners((ContextRefreshedEvent ev) -> {
            logger.info("########## ContextRefreshedEvent");
        });
        app.run(args);
        //ConfigurableApplicationContext applicationContext =  SpringApplication.run(XmljpademoApplication.class, args);
    }
}
