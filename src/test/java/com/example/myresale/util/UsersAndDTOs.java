package com.example.myresale.util;

import com.example.myresale.DTOs.ItemCreateRequestDTO;
import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.entities.UserInfo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UsersAndDTOs {
    public static UserInfoCreateDTO validUserDto(){
        return new UserInfoCreateDTO("Username", "Password", "email@gmail.com");
    }

    public static UserInfoCreateDTO invalidUserDto(){
        return new UserInfoCreateDTO("", "", "");
    }
    public static UserInfo validUserInfo(){
        return new UserInfo(1L,"Username", "Password", "email@gmail.com",new ArrayList<>(), new ArrayList<>());
    }

    public static ItemCreateRequestDTO validCreateRequestDto(){
        return new ItemCreateRequestDTO("Spring", "Description", "Spike", new BigDecimal(100), "url", validUserInfo());
    }

    public static ItemCreateRequestDTO invalidCreateRequestDto() {
        return new ItemCreateRequestDTO();
    }
}
