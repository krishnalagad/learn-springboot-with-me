package com.revise.image_upload.controller;

import com.revise.image_upload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v2/pdf")
public class PDFController {

    @Autowired
    private ImageService imageService;

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
}
