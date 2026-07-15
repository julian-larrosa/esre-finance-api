package com.esre.auth.service;

import com.esre.auth.dto.request.LoginRequest;
import com.esre.auth.dto.request.RegisterRequest;
import com.esre.auth.dto.response.AuthResponse;

public interface AuthService {

    public AuthResponse register(RegisterRequest request);
    public AuthResponse login(LoginRequest request);


}
