package com.example.myresale.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String username;
    private String password;
    private String email;
}
