package com.stock.stockbackend.controller;

import com.stock.stockbackend.dto.AuthResponse;
import com.stock.stockbackend.dto.LoginRequest;
import com.stock.stockbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
