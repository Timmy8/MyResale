package com.example.myresale.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateItemRequestDTO {
    @NotNull(message = "Incorrect name: name can't be null!")
    @NotBlank(message = "Incorrect name: name can't be blank!")
    @Size(min = 5, max = 30, message = "Incorrect name: must be 5 - 30 symbols!")
    private String name;

    @Size(max = 100, message = "Incorrect description: max 30 symbols!")
    private String description;

    @Size(max = 30, message = "Incorrect author, max 30 symbols!")
    private String author;

    @NotNull(message = "Incorrect price: price can't be null!")
    @DecimalMin(value = "1", message = "Incorrect price: min 1!")
    @DecimalMax(value = "1000", message = "Incorrect price: max 1000!")
    private BigDecimal price;

    private String imageURL;

}
