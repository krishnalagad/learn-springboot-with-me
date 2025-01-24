package com.learnspring.interceptors.springboot_interceptor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() throws InterruptedException {
        logger.info("Fixed Delay Task - Start Time: {}", LocalDateTime.now().format(formatter));

        // Simulate a task that takes 3 seconds to execute
        Thread.sleep(3000);

        logger.info("Fixed Delay Task - End Time: {}", LocalDateTime.now().format(formatter));

    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() throws InterruptedException {
        logger.info("Fixed Rate Task - Start Time: {}", LocalDateTime.now().format(formatter));

        // Simulate a task that takes 3 seconds to execute
        Thread.sleep(3000);

        logger.info("Fixed Rate Task - End Time: {}", LocalDateTime.now().format(formatter));

    }

}