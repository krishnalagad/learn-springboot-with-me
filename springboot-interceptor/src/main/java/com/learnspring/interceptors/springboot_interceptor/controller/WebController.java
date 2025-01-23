package com.learnspring.interceptors.springboot_interceptor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api")
public class WebController {

    private final static Logger logger = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/greet")
    public ResponseEntity<Object> greet() throws InterruptedException {
        Thread.sleep(2000);
        logger.info(String.format("Inside controller (/greet) time: %s", this.getCurrentTimeWithMillis()));
        return ResponseEntity.ok(new LinkedHashMap<>() {{
            put("id", "Greet");
            put("message", "Welcome user, Its greet REST API.");
        }});
    }

    @GetMapping("/deny")
    public ResponseEntity<Object> deny() {
        return ResponseEntity.ok(new LinkedHashMap<>() {{
            put("id", "Deny");
            put("message", "Get lost from here, Its Deny REST API.");
        }});
    }

    private String getCurrentTimeWithMillis() {
        // Get the current time in the Indian time zone
        LocalTime indianTime = LocalTime.now(ZoneId.of("Asia/Kolkata"));

        // Format the time with milliseconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        return indianTime.format(formatter);
    }
}