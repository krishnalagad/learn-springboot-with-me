package com.revise.image_upload.controller;

import com.revise.image_upload.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v2/pdf")
public class PDFController {

    @Autowired
    private ImageService imageService;

    @Value("${project.image}")
    private String path;

    @Value("${project.file}")
    private String filePath;

    Logger logger = LoggerFactory.getLogger(PDFController.class);

    @PostMapping("/read")
    public ResponseEntity<Map<String, String>> readPDF(@RequestParam("file") MultipartFile file) {
        Map<String, String> resp = new HashMap<>();
        try {
            String content = this.imageService.readPDF(file.getInputStream());
//            content = "hello my name is krishna dilip lagad. My email id is krishna@gmail.com and my friends email id is aakanksha@gmail.com";
            resp.put("WordCount", Integer.toString(this.getWordCount(content)));
            resp.put("Emails", this.extractEmails(content).toString());
            return ResponseEntity.ok(resp);
        } catch (IOException e) {
            e.printStackTrace();
            resp.put("Message", "Failed to read PDF file.");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PostMapping("/upload-all/{id}")
    public ResponseEntity<?> uploadMultipleFiles(@PathVariable Integer id, @RequestParam("files") MultipartFile[] files) {
        this.logger.info("Id: {}", id);
        Arrays.stream(files).forEach(file -> {
            try {
                this.imageService.uploadImage(filePath, file, Integer.toString(id));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok("{\"message\": \"Files uploaded successfully !!\"}");
    }

//    --------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------Helper methods ---------------------------------------------------------

    private int getWordCount(String content) {
        return content.split(" ").length;
    }

    private List<String> extractEmails(String paragraph) {
        List<String> emails = new ArrayList<>();
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(paragraph);

        while (matcher.find()) {
            String email = matcher.group();
            emails.add(email);
        }

        return emails;
    }

    private void tokenize(String text) {
        
    }
}
