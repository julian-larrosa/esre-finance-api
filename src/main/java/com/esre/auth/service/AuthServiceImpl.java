package com.esre.auth.service;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esre.auth.dto.request.LoginRequest;
import com.esre.auth.dto.request.RegisterRequest;
import com.esre.auth.dto.response.AuthResponse;
import com.esre.auth.exception.UnauthorizedException;
import com.esre.auth.mapper.AuthMapper;
import com.esre.category.exception.ResourceAlreadyExistsException;
import com.esre.common.exception.AccessDeniedException;
import com.esre.user.entity.User;
import com.esre.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    
    @Override
    public AuthResponse register(RegisterRequest request) {
        try{
            if(userService.existsByEmail(request.getEmail())){
                throw new ResourceAlreadyExistsException("El usuario ya está registrado");
            }

            if (!request.getPassword().equals(request.getConfirmPassword())){
                throw new RuntimeException("no coinciden las contraseñas");
            }
            User usuario = authMapper.toEntity(request);
            userService.setPassword(usuario, passwordEncoder.encode(request.getPassword()));
            User usuarioNuevo = userService.saveUser(usuario);
            return authMapper.toResponse(usuarioNuevo);
        } catch (Exception exception) {
            throw new RuntimeException("Ocurrió un error al registrar el usuario", exception);
        }
    }


    @Override
    public AuthResponse login(LoginRequest request) {
        try{
            User user = userService.findByEmail(request.getEmail());
            if(!user.getIsActive()){
                throw new AccessDeniedException("Usuario inactivo");
            }
            if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
                throw new UnauthorizedException("credenciales incorrectas");
            }
            String token = tokenProvider.generateToken(user);
            AuthResponse response = authMapper.toResponse(user);
            response.setToken(token);
            response.setExpiresIn(tokenProvider.getExpiration());
            return response;
        } catch (Exception exception) {
            throw new RuntimeException("Ocurrió un error al registrar el usuario", exception);
        }
    }

}
