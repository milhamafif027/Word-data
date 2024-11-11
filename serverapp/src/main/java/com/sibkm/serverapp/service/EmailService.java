package com.sibkm.serverapp.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.sibkm.serverapp.model.request.EmailRequest;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine; // Tambahkan SpringTemplateEngine

    public EmailRequest sendEmailTemplate(EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set email details
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            // Prepare template variables for Thymeleaf
            Context context = new Context();
            context.setVariable("name", emailRequest.getName());  // Sesuaikan dengan model Anda
            context.setVariable("barang", emailRequest.getBarang()); // Sesuaikan dengan model Anda
            
            // Generate the HTML content using Thymeleaf template
            String htmlContent = templateEngine.process("TemplateEmail", context);
            helper.setText(htmlContent, true); // Set HTML content

            // Send the email
            javaMailSender.send(message);
            log.info("Successfully sent email...");
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
        return emailRequest;
    }
}

