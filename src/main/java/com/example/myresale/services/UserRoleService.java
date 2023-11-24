package com.example.myresale.services;

import com.example.myresale.entities.UserRole;
import com.example.myresale.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private UserRoleRepository roleRepository;

    public UserRoleService(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserRole findRoleByName(String name){
        return roleRepository.findByName(name);
    }
}
