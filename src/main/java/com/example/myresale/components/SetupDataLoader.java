package com.example.myresale.components;

import com.example.myresale.DTOs.ItemCreateRequestDTO;
import com.example.myresale.DTOs.UserInfoCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.entities.UserRole;
import com.example.myresale.services.ItemService;
import com.example.myresale.services.UserInfoDetailsService;
import com.example.myresale.services.UserRoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${security.starter.data.setup}")
    private Boolean need_starter_data;
    @Value("${security.admin.login}")
    private String admin_username;

    @Value("${security.admin.password}")
    private String admin_password;

    @Value("${security.admin.email}")
    private String admin_email;

    private boolean alreadySetup = false;
    private final UserRoleService roleService;
    private final UserInfoDetailsService userService;
    private final ItemService itemService;

    public SetupDataLoader(UserRoleService roleService, UserInfoDetailsService userService, ItemService itemService) {
        this.roleService = roleService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        for (UserRoleEnum role : UserRoleEnum.values())
            roleService.createRoleIfNotExists(role.name());

        // Admin user and starter items adding
        setupStarterData();

        alreadySetup = true;
    }

    private void setupStarterData(){
        if (!need_starter_data)
            return;
        // Setup admin before start
        var user = userService.saveUserInfo(new UserInfoCreateDTO(admin_username, admin_password, admin_email));
        userService.addRole(user.getUsername(), UserRoleEnum.ROLE_ADMIN);
        // Setup items before start
        var items = List.of(
                new ItemCreateRequestDTO("Spring bystro", "Good guide for spring newbee", "Spilke", BigDecimal.valueOf(200.50), "images/book.png", user),
                new ItemCreateRequestDTO("Spring bystro", "Good guide for spring newbee", "Spilke", BigDecimal.valueOf(200.50), "images/book.png", user),
                new ItemCreateRequestDTO("Summer medlenno", " Not guide", "Spider man", BigDecimal.valueOf(404.40), "images/summer_vibe.jpg", user),
                new ItemCreateRequestDTO("Spring medlenno", "Badguide for spring newbee", "Spilkel", BigDecimal.valueOf(50.40), "images/book.png", user),
                new ItemCreateRequestDTO("Spring bystro", "Good guide for spring newbee", "Spilke", BigDecimal.valueOf(200.50), "images/book.png", user),
                new ItemCreateRequestDTO("Spring bystro", "Good guide for spring newbee", "Spilke", BigDecimal.valueOf(200.50), "images/book.png", user),
                new ItemCreateRequestDTO("Brave New World", "Brave New World is a dystopian novel by English author Aldous Huxley, written in 1931", "Aldous Huxley", BigDecimal.valueOf(999.00), "images/Brave_New_World.jpg", user)
        );

        items.forEach(itemService::addItem);
    }
}
