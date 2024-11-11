package com.sibkm.clientapp.controller.rest;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sibkm.clientapp.entity.request.LoginRequest;
import com.sibkm.clientapp.entity.response.LoginResponse;
import com.sibkm.clientapp.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/login")
public class RestAuthController {
    private AuthService authService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
