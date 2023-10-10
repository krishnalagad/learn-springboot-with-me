package com.learnspring.boot_320.csv_to_mysql_springbatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    public String saveFileToDirectory(MultipartFile file) throws Exception{

        String originalFilename = file.getOriginalFilename();

        String filePath = "/src/main/resources/" + originalFilename;
        try{
            long copy = Files.copy(file.getInputStream(), Paths.get(filePath));
            logger.info("{}", copy);
        } catch (Exception e) {
            logger.info("Error in copying file bytes to desired folder.");
            e.printStackTrace();
            throw new RuntimeException();
        }

        return filePath;
    }
}