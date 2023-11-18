package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String city;
    private String street;
    private String houseNumber;
    private String zip;
    private String phoneNumber;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserInfo createdBy;
}
