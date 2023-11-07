package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String author;
    private BigDecimal price;
    private String imageURL;
}
