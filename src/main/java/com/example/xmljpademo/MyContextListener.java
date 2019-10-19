package com.example.xmljpademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(MyContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        logger.info("########### ServletContextName: {}",servletContext.getServletContextName());
        servletContext.setAttribute("name", "robbie");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        logger.info((String) servletContext.getAttribute("name"));
    }
}
