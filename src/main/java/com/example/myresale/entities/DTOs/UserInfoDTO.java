package com.example.myresale.entities.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    @NotNull(message = "Incorrect username, can't be null!")
    @NotBlank(message = "username can't be blank!")
    private String username;

    @NotNull(message = "Incorrect password, can't be null!")
    @Size(min = 5, max = 20, message = "Password must be 5-15 symbols!")
    @Pattern(regexp = "^.*[A-Z]+.*$", message = "Password must have min 1 uppercase letter!")
    private String password;

    @NotNull(message = "Incorrect email address, can't be null!")
    @NotEmpty(message = "Email can't be empty!")
    @Email(message = "Incorrect email, must have email address format!")
    private String email;
}
