package com.example.myresale.repositories;

import com.example.myresale.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findUserInfoByUsername(String username);
}
