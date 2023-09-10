package com.revise.mail_sender;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {

    // Method To send a simple email
    boolean sendSimpleMail(EmailData data);

    // Method To send an email with attachment
    boolean sendMailWithAttachment(EmailData data, MultipartFile file) throws IOException;

    // Method to send an email with multiple attachments.
    boolean sendMailWithAttachments(EmailData data, MultipartFile[] files) throws IOException;
}
