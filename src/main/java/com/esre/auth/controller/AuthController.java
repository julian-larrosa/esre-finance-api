package com.esre.auth.controller;

import com.esre.auth.dto.request.LoginRequest;
import com.esre.auth.dto.request.RegisterRequest;
import com.esre.auth.dto.response.AuthResponse;
import com.esre.auth.service.AuthService;
import com.esre.common.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticación")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "usuario creado exitosamente")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea una cuenta de usuario con email y contraseña")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request){
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ApiResponse.created(response));                     
    }
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "login exitoso")
    @Operation(summary = "Loguear usuario", description = "Loguea una cuenta de usuario con email y contraseña")
    public ResponseEntity<ApiResponse<AuthResponse>> login (@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(ApiResponse.ok(response));
    }
}
