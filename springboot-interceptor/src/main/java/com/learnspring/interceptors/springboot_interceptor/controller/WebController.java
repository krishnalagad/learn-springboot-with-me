package com.learnspring.interceptors.springboot_interceptor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
        // Get the current date and time in the Indian time zone
        LocalDateTime indianTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedIndianTime = indianTime.format(formatter);

        logger.info(String.format("Inside controller (/greet) time: %s", formattedIndianTime));
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
}