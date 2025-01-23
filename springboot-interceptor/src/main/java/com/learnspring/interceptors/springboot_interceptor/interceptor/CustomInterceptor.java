package com.learnspring.interceptors.springboot_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(String.format("PreHandle called at: %s", this.getCurrentTimeWithMillis()));
        logger.info(String.format("Request URL -> %s", request.getRequestURL()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info(String.format("PostHandle called at: %s", this.getCurrentTimeWithMillis()));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info(String.format("AfterCompletion called at: %s", this.getCurrentTimeWithMillis()));
    }

    private String getCurrentTimeWithMillis() {
        // Get the current time in the Indian time zone
        LocalTime indianTime = LocalTime.now(ZoneId.of("Asia/Kolkata"));

        // Format the time with milliseconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        return indianTime.format(formatter);
    }
}