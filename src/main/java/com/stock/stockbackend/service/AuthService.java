package com.stock.stockbackend.service;

import com.stock.stockbackend.dto.AuthResponse;
import com.stock.stockbackend.dto.LoginRequest;
import com.stock.stockbackend.dto.RegisterRequest;
import com.stock.stockbackend.model.User;
import com.stock.stockbackend.repository.UserRepository;
import com.stock.stockbackend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña inválida");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
