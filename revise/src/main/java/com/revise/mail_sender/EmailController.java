package com.revise.mail_sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/attachment")
    public ResponseEntity<?> sendEmail(@RequestParam("emailData") String data,
                                       @RequestParam("file") MultipartFile file) throws IOException {
        EmailData emailData = null;

        // converting string into JSON.
        try {
            emailData = this.mapper.readValue(data, EmailData.class);
        }catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request !!");
        }
        boolean resp = this.emailService.sendMailWithAttachment(emailData, file);

        if(resp)
            return ResponseEntity.ok("Email sent successfully !!");

        return ResponseEntity.status(HttpStatus.OK).body("Fail to send email !!");

    }
}
