package com.example.myresale.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDeleteRequestDTO {
    @NotNull(message = "Deleting id can't be empty!")
    @PositiveOrZero(message = "Deleting id must be number!")
    private Long id;

    @NotNull(message = "Deleting reason can't be null!")
    @NotBlank(message = "Deleting reason can't be blank!")
    private String reason;
}
