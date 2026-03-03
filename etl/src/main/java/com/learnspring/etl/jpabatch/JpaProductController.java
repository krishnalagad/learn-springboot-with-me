package com.learnspring.etl.jpabatch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jpa")
public class JpaProductController {

    private final JpaProductService jpaProductService;

    public JpaProductController(JpaProductService jpaProductService) {
        this.jpaProductService = jpaProductService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file.");
        }
        String result = jpaProductService.uploadCsvData(file);
        return ResponseEntity.ok(result);
    }
}
