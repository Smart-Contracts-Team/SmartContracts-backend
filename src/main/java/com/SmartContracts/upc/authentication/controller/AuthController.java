package com.SmartContracts.upc.authentication.controller;

import com.SmartContracts.upc.authentication.model.AuthResponse;
import com.SmartContracts.upc.authentication.model.LoginRequest;
import com.SmartContracts.upc.authentication.model.RegisterRequest;
import com.SmartContracts.upc.authentication.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/smartcontract/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/auth/register
    // Method: POST

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerInfluencer(@RequestBody RegisterRequest request){
        authService.existsUserByEmail(request);
        authService.validateRegisterRequest(request);

        return new ResponseEntity<AuthResponse>(authService.register(request), HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/auth/login
    // Method: POST
    @Transactional(readOnly = true)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse>loginInfluencer(@RequestBody LoginRequest request){
        AuthResponse logged = authService.login(request);
        return new ResponseEntity<AuthResponse>(logged, HttpStatus.OK);
    }
}
