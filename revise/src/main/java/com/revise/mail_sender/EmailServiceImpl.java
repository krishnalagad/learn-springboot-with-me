package com.revise.mail_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public boolean sendSimpleMail(EmailData data) {

        try{
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(data.getRecipient());
            mailMessage.setText(data.getMsgBody());
            mailMessage.setSubject(data.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendMailWithAttachment(EmailData data, MultipartFile file) throws IOException {

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            // Setting multipart true for attachment to be sent.
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(this.sender);
            mimeMessageHelper.setTo(data.getRecipient());
            mimeMessageHelper.setSubject(data.getSubject());
            mimeMessageHelper.setText(data.getMsgBody());
            
            // Adding attachment
            FileSystemResource attachmentFile = new FileSystemResource(this.convertMultiPartToFile(file));
            mimeMessageHelper.addAttachment(attachmentFile.getFilename(), attachmentFile);

            this.javaMailSender.send(mimeMessage);

            return true;

        } catch(MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean sendMailWithAttachments(EmailData data, MultipartFile[] files) throws IOException {

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            // Setting multipart true for attachment to be sent.
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(this.sender);
            mimeMessageHelper.setTo(data.getRecipient());
            mimeMessageHelper.setSubject(data.getSubject());
            mimeMessageHelper.setText(data.getMsgBody());

            Arrays.stream(files).forEach(file -> {
                // Adding attachments
                FileSystemResource attachmentFile = null;
                try {
                    attachmentFile = new FileSystemResource(this.convertMultiPartToFile(file));
                    mimeMessageHelper.addAttachment(attachmentFile.getFilename(), attachmentFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            this.javaMailSender.send(mimeMessage);
            return true;

        } catch(MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
