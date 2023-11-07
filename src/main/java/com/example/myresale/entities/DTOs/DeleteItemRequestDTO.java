package com.example.myresale.entities.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteItemRequestDTO {
    private long id;

    @NotNull(message = "Deleting reason can't be null!")
    private String reason;
}
