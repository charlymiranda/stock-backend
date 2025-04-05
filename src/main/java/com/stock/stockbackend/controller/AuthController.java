package com.stock.stockbackend.controller;

import com.stock.stockbackend.dto.AuthResponse;
import com.stock.stockbackend.dto.LoginRequest;
import com.stock.stockbackend.dto.RegisterRequest;
import com.stock.stockbackend.dto.UserResponseDTO;
import com.stock.stockbackend.repository.UserRepository;
import com.stock.stockbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        var user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UserResponseDTO(user.getEmail(), user.getRole());
    }

}
