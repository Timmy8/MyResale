package com.example.myresale.services;

import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.repositories.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserInfoDetailsService implements UserDetailsService {
    private UserInfoRepository repository;
    private PasswordEncoder encoder;

    public UserInfoDetailsService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findUserInfoByUsername(username);
        if (userInfo != null) return userInfo;
        else throw new UsernameNotFoundException("User '" + username + "' not found!");
    }

    public UserInfo saveUserInfo(UserInfoCreateDTO userInfoDTO){

        UserInfo userInfo = UserInfo.builder()
                .username(userInfoDTO.getUsername())
                .password(encoder.encode(userInfoDTO.getPassword()))
                .email(userInfoDTO.getEmail())
                .build();

        return repository.save(userInfo);
    }
}
