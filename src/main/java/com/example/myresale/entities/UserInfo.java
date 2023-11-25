package com.example.myresale.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private final Set<UserRole> roles = new HashSet<>();

    // Reference to user's shopping cart
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", nullable = false, unique = true)
    private final UserCart userCart = new UserCart();

    // List of items created by the user
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<Item> items = new ArrayList<>();

    // List of delivery addresses added by the user
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<DeliveryAddress> deliveryAddresses = new ArrayList<>();

    public void addRole(UserRole role){
        roles.add(role);
    }

    // User Details implementations
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
