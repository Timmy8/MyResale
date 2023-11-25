package com.example.myresale.components;

import com.example.myresale.services.UserInfoDetailsService;
import com.example.myresale.services.UserRoleService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final UserRoleService roleService;

    public SetupDataLoader(UserRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        for (UserRoleEnum role : UserRoleEnum.values())
            roleService.createRoleIfNotExists(role.name());

        alreadySetup = true;
    }
}
