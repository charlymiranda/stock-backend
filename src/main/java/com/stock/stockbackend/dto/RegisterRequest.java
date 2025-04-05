package com.stock.stockbackend.dto;

import com.stock.stockbackend.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}
