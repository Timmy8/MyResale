package com.example.myresale.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;


    private String description;

    @NotNull
    @DecimalMin(value = "10.0")
    @DecimalMax(value = "1000.0")
    private BigDecimal price;

    private String imageURL;
}
