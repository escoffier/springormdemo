package com.example.xmljpademo.controller;

import com.example.xmljpademo.model.scopes.HelloMessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/scopes")
public class ScopesController {

    final static Logger logger = LoggerFactory.getLogger(ScopesController.class);

    @Resource(name = "requestScopedBean")
    HelloMessageGenerator requestScopedBean;

    @Resource(name = "sessionScopedBean")
    HelloMessageGenerator sessionScopedBean;

    @GetMapping("/scopes/request")
    public String getRequestScopeMessage() {
        logger.info("request-scope bean message: {}", requestScopedBean.getMessage());
        return requestScopedBean.getMessage();
    }

    @GetMapping("/scopes/session")
    public String getSessionScopeMessage() {
        logger.info("session-scope bean message: {}", sessionScopedBean.getMessage());
        return sessionScopedBean.getMessage();
    }
}
