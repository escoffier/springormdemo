package com.example.xmljpademo.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("########## path {}" ,httpServletRequest.getPathInfo() );
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
