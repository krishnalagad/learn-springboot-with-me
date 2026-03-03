package com.learnspring.etl.loaddatainfile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/loaddata")
public class LoadDataProductController {

    private final LoadDataProductService loadDataProductService;

    public LoadDataProductController(LoadDataProductService loadDataProductService) {
        this.loadDataProductService = loadDataProductService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file.");
        }
        String result = loadDataProductService.uploadCsvData(file);
        return ResponseEntity.ok(result);
    }
}
