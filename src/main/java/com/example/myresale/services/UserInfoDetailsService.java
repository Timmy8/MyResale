package com.example.myresale.services;

import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.components.UserRoleEnum;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.entities.UserRole;
import com.example.myresale.exceptions.EmailAlreadyExistsException;
import com.example.myresale.exceptions.UserExistsException;
import com.example.myresale.exceptions.UsernameExistsException;
import com.example.myresale.repositories.UserInfoRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;


@Service
public class UserInfoDetailsService implements UserDetailsService {
    private final UserInfoRepository repository;
    private final UserRoleService roleService;
    private final PasswordEncoder encoder;

    public UserInfoDetailsService(UserInfoRepository repository, PasswordEncoder encoder, UserRoleService roleService) {
        this.repository = repository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findUserInfoByUsername(username);
        if (userInfo != null) return userInfo;
        else throw new UsernameNotFoundException("User '" + username + "' not found!");
    }

    @Transactional
    public UserInfo saveUserInfo(UserInfoCreateDTO userInfoDTO){

        if (repository.existsUserInfoByUsername(userInfoDTO.getUsername())){
            throw new UsernameExistsException();
        }

        if (repository.existsUserInfoByEmail(userInfoDTO.getEmail()))
            throw new EmailAlreadyExistsException();



        UserInfo userInfo = UserInfo.builder()
                .username(userInfoDTO.getUsername())
                .password(encoder.encode(userInfoDTO.getPassword()))
                .email(userInfoDTO.getEmail())
                .build();
        userInfo.addRole(roleService.findRoleByName(UserRoleEnum.ROLE_USER.name()));

        return repository.save(userInfo);
    }

    public void addRole(String username, UserRoleEnum role){
        repository.findUserInfoByUsername(username).addRole(roleService.findRoleByName(role.name()));
    }
}
