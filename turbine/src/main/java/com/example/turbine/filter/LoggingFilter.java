package com.example.turbine.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.info("[REQUEST START]===========================================================");
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        logger.info(method + " " + requestURI);
        for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements();) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            logger.info(headerName + ": " + headerValue);
        }
        logger.info("[REQUEST END]===========================================================");
        chain.doFilter(request, response);
        logger.info("[RESPONSE START]===========================================================");
        int status = response.getStatus();
        logger.info(String.valueOf(status));
        for (String headerName: response.getHeaderNames()) {
            String headerValue = response.getHeader(headerName);
            logger.info(headerName + ": " + headerValue);
        }
        logger.info("[RESPONSE END]===========================================================");
    }

}
