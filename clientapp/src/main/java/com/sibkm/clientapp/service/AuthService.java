package com.sibkm.clientapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sibkm.clientapp.entity.request.LoginRequest;
import com.sibkm.clientapp.entity.request.RegistrationRequest;
import com.sibkm.clientapp.entity.response.LoginResponse;
import com.sibkm.clientapp.entity.response.RegistrationResponse;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    public LoginResponse login(LoginRequest loginRequest){
        return restTemplate
            .exchange("http://localhost:8081/login", 
            HttpMethod.POST, new HttpEntity<LoginRequest>(loginRequest), 
            new ParameterizedTypeReference<LoginResponse>() {
            }).getBody();
    }

    public RegistrationResponse registration(RegistrationRequest registrationRequest){
        return restTemplate
            .exchange("http://localhost:8081/registration", 
            HttpMethod.POST, new HttpEntity<RegistrationRequest>(registrationRequest),
            new ParameterizedTypeReference<RegistrationResponse>() {
            }).getBody();
    }
}
