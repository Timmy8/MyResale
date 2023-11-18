package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private DeliveryAddress deliveryAddress;
    @ManyToOne
    private UserInfo userInfo;
    @ManyToOne
    private Item item;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date createdAt = new Date();
}
