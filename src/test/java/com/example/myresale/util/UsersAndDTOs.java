package com.example.myresale.util;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.DTOs.ItemCreateRequestDTO;
import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.entities.UserRole;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class UsersAndDTOs {
    public static UserInfoCreateDTO validUserDto(){
        return new UserInfoCreateDTO("Username", "Password", "email@gmail.com");
    }

    public static UserInfoCreateDTO invalidUserDto(){
        return new UserInfoCreateDTO("", "", "");
    }
    public static UserInfo validUserInfo(){
        var user = new UserInfo(1L, "username", "password", "email", new ArrayList<>(), new ArrayList<>());
        user.addRole(new UserRole(1L, "ROLE_USER", new HashSet<>()));

        return user;
    }

    public static ItemCreateRequestDTO validCreateRequestDto(){
        return new ItemCreateRequestDTO("Spring", "Description", "Spike", new BigDecimal(100), "url", validUserInfo());
    }

    public static ItemCreateRequestDTO invalidCreateRequestDto() {
        return new ItemCreateRequestDTO();
    }

    public static DeliveryAddressCreateDTO validDeliveryAddress(){
        return new DeliveryAddressCreateDTO("1","1","1","1","1","1" );
    }
}
