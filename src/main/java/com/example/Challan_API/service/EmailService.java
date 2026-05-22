package com.example.Challan_API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendChallanReceipt(String toEmail, byte[] pdfBytes, String fileName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

          //  helper.setTo(toEmail);
            helper.setTo("tyagi18preet2000@gmail.com");
            helper.setSubject("Challan Payment Receipt");
            helper.setText("Dear User,\n\nYour challan payment is successful. Find the attached receipt.\n\nThank you.");

            helper.addAttachment(fileName, new ByteArrayResource(pdfBytes));

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed: " + e.getMessage());
        }
    }
    
   
    
    
}