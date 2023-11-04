package com.example.myresale.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteRequestDTO {
    private long id;

    @NotNull(message = "Deleting reason can't be null!")
    private String reason;
}
