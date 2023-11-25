package com.example.myresale.services;

import com.example.myresale.entities.UserRole;
import com.example.myresale.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleService {
    private UserRoleRepository roleRepository;

    public UserRoleService(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserRole findRoleByName(String name){
        return roleRepository.findByName(name);
    }

    @Transactional
    public void createRoleIfNotExists(String name){
        UserRole role = roleRepository.findByName(name);
        if (role == null)
            roleRepository.save(UserRole.builder().name(name).build());
    }
}
