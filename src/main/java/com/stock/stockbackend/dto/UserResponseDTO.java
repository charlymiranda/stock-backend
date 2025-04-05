package com.stock.stockbackend.dto;

import com.stock.stockbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String email;
    private Role role;
}
