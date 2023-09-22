package com.learnspring.boot_320.csv_to_mysql_apache.controller;

import com.learnspring.boot_320.csv_to_mysql_apache.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/v1/csv")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        if (this.fileService.hasCsvFormat(file)) {
            this.fileService.processAndSaveData(file);
            return ResponseEntity.ok("File uploaded and data saved successfully: " + file.getOriginalFilename());
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Please upload CSV file only.");
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile() {
        String fileName = "download_data.csv";
        ByteArrayInputStream fileData = this.fileService.load();
        InputStreamResource resource = new InputStreamResource(fileData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }
}
