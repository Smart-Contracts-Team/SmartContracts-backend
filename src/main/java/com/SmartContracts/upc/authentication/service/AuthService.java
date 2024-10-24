package com.SmartContracts.upc.authentication.service;

import com.SmartContracts.upc.authentication.model.AuthResponse;
import com.SmartContracts.upc.authentication.model.LoginRequest;
import com.SmartContracts.upc.authentication.model.RegisterRequest;

public interface AuthService {
    public abstract AuthResponse register(RegisterRequest registerRequest);
    public abstract AuthResponse login(LoginRequest loginRequest);
    public void validateRegisterRequest(RegisterRequest registerRequest);
    public void existsUserByEmail(RegisterRequest registerRequest);
}
