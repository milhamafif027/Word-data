package com.sibkm.clientapp.controller.rest;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sibkm.clientapp.entity.request.LoginRequest;
import com.sibkm.clientapp.entity.request.RegistrationRequest;
import com.sibkm.clientapp.entity.response.LoginResponse;
import com.sibkm.clientapp.entity.response.RegistrationResponse;
import com.sibkm.clientapp.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RestAuthController {
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/registration")
    public RegistrationResponse registration(@RequestBody RegistrationRequest registrationRequest){
        return authService.registration(registrationRequest);
    }
}
