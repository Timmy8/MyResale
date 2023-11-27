package com.example.myresale.util;

import com.example.myresale.entities.UserInfo;
import com.example.myresale.entities.UserRole;

import java.util.ArrayList;
import java.util.HashSet;

public class UserRepo {
    public static UserInfo validUser(){
        var user = new UserInfo(1L, "username", "password", "email", new ArrayList<>(), new ArrayList<>());
        user.addRole(new UserRole(1L, "ROLE_USER", new HashSet<>()));

        return user;
    }
}
