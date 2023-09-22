package com.learnspring.boot_320.csv_to_mysql_apache.controller;

import com.learnspring.boot_320.csv_to_mysql_apache.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/csv")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        if (this.fileService.hasCsvFormat(file)) {
            this.fileService.processAndSaveData(file);
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Please upload CSV file only.");
    }
}
