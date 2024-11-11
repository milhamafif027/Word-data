package com.sibkm.serverapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sibkm.serverapp.model.request.EmailRequest;
import com.sibkm.serverapp.service.EmailService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private EmailService emailService;

    @PostMapping("/template")
    public EmailRequest sendEmailTemplate(@RequestBody EmailRequest emailRequest){
        return emailService.sendEmailTemplate(emailRequest);
    }
}
