package com.example.myresale.configuration;

import com.example.myresale.services.UserInfoDetailsService;
import com.example.myresale.telegramBot.MyResaleNotificationBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//CTRL + ALT + B -> посмотреть доступные реализации

@Configuration
public class ProjectConfiguration implements WebMvcConfigurer {
    @Autowired
    @Lazy
    UserInfoDetailsService service;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(HttpMethod.POST, "/delete/**").hasRole("MODERATOR")
                            .requestMatchers(HttpMethod.POST, "/create/**").hasRole("USER")
                            .requestMatchers("/logout", "/cart/**", "/purchase/allCartPurchase", "/create", "/delete/**").hasRole("USER")
                            .requestMatchers("/login", "/registration").anonymous()
                            .requestMatchers("/css/**", "/images/**").permitAll()
                            .requestMatchers("/", "/items/**", "/purchase/**", "/api/**").permitAll();
                })
                .formLogin(form -> {
                    form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/items", false)
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .permitAll();

                })
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/items")
                        .permitAll())
                .authenticationProvider(authProvider());

        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(encoder());

        return authenticationProvider;
    }

    // WebMvcConfigures overrides
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/items");
        registry.addViewController("/api/items/delete").setViewName("form_item_deletion.html");
    }


}
