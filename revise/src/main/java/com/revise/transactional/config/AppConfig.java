package com.revise.transactional.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
@EnableCaching
public class AppConfig implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("Request URL: {}", request.getRequestURL().toString());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}