package com.fivepoints.spring.controllers;

import com.fivepoints.spring.exceptions.EmailAlreadyUsedException;
import com.fivepoints.spring.payload.requests.LoginRequest;
import com.fivepoints.spring.payload.requests.RegisterRequest;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest)
    {
        this.authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        String message = this.authService.register(registerRequest);
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
