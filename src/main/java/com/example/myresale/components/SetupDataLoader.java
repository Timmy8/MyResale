package com.example.myresale.components;

import com.example.myresale.entities.UserRole;
import com.example.myresale.repositories.UserInfoRepository;
import com.example.myresale.repositories.UserRoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final UserRoleRepository roleRepository;

    public SetupDataLoader(UserRoleRepository roleRepository, UserInfoRepository userInfoRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        for (UserRoleEnum role : UserRoleEnum.values())
            createRoleIfNotExists(role.name());

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotExists(String name){
        UserRole role = roleRepository.findByName(name);
        if (role == null)
            roleRepository.save(UserRole.builder().name(name).build());
    }
}
