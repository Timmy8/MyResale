package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_from_purchase_order",
            joinColumns = @JoinColumn(name = "purchase_order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private Set<Item> items = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private final Date createdAt = new Date();
}
