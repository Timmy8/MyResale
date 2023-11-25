package com.example.myresale.repositories;

import com.example.myresale.entities.UserCart;
import com.example.myresale.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findUserInfoByUsername(String username);
    boolean existsUserInfoByUsername(String username);
    boolean existsUserInfoByEmail(String email);
}
