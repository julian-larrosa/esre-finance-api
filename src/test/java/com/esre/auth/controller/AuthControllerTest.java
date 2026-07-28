package com.esre.auth.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.esre.auth.dto.request.LoginRequest;
import com.esre.auth.dto.request.RegisterRequest;
import com.esre.auth.dto.response.AuthResponse;
import com.esre.auth.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.esre.auth.filter.JwtAuthenticationFilter;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void register_WhenRequestIsValid_ShouldReturnCreated() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("usuario@email.com");
        request.setPassword("Password123");
        request.setConfirmPassword("Password123");

        AuthResponse response = new AuthResponse();
        response.setEmail("usuario@email.com");
        response.setToken("jwt-token");
        response.setExpiresIn(86400000L);

        when(authService.register(any(RegisterRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Creado exitosamente"))
                .andExpect(jsonPath("$.data.email").value("usuario@email.com"))
                .andExpect(jsonPath("$.data.token").value("jwt-token"))
                .andExpect(jsonPath("$.data.expiresIn").value(86400000L))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(authService).register(any(RegisterRequest.class));
    }
    
    @Test
    void login_WhenRequestIsValid_ShouldReturnOk() throws Exception {
    LoginRequest request = new LoginRequest();
    request.setEmail("usuario@email.com");
    request.setPassword("Password123");

    AuthResponse response = new AuthResponse();
    response.setEmail("usuario@email.com");
    response.setToken("jwt-token");
    response.setExpiresIn(86400000L);

    when(authService.login(any(LoginRequest.class)))
            .thenReturn(response);

    mockMvc.perform(post("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(200))
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data.email").value("usuario@email.com"))
            .andExpect(jsonPath("$.data.token").value("jwt-token"))
            .andExpect(jsonPath("$.data.expiresIn").value(86400000L))
            .andExpect(jsonPath("$.timestamp").exists());

    verify(authService).login(any(LoginRequest.class));
    }
}

