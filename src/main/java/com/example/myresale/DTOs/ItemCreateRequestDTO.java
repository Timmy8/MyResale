package com.example.myresale.DTOs;

import com.example.myresale.entities.UserInfo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateRequestDTO {
    @NotNull(message = "Incorrect name: name can't be null!")
    @NotBlank(message = "Incorrect name: name can't be blank!")
    @Size(min = 5, max = 30, message = "Incorrect name: must be 5 - 30 symbols!")
    private String name;

    @Size(max = 100, message = "Incorrect description: max 100 symbols!")
    private String description;

    @Size(max = 30, message = "Incorrect author, max 30 symbols!")
    @NotBlank(message = "Your item must have an author!")
    private String author;

    @NotNull(message = "Incorrect price: price can't be null!")
    @DecimalMin(value = "1", message = "Incorrect price: min 1!")
    @DecimalMax(value = "1000", message = "Incorrect price: max 1000!")
    private BigDecimal price;

    private String imageURL;

    private UserInfo createdBy;

}
