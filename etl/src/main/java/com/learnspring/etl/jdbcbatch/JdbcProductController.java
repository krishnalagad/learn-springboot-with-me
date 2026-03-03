package com.learnspring.etl.jdbcbatch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jdbc")
public class JdbcProductController {

    private final JdbcProductService jdbcProductService;

    public JdbcProductController(JdbcProductService jdbcProductService) {
        this.jdbcProductService = jdbcProductService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file.");
        }
        String result = jdbcProductService.uploadCsvData(file);
        return ResponseEntity.ok(result);
    }
}
