package com.example.myresale.configuration;

import com.example.myresale.services.UserInfoDetailsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//CTRL + ALT + B -> посмотреть доступные реализации

@Configuration
@EnableWebSecurity
public class ProjectConfiguration {
    @Autowired
    @Lazy
    UserInfoDetailsService service;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http
                 .authorizeHttpRequests(request -> {request
                         .requestMatchers("/login", "/registration", "/items").permitAll()
                         .requestMatchers("/items/{id}").hasRole("USER")
                         .anyRequest().authenticated();
                 })
                 .formLogin(form ->{form
                         .loginPage("/login")
                         .loginProcessingUrl("/login")
                         .defaultSuccessUrl("/items", true)
                         .failureForwardUrl("/login?error=true")
                         .usernameParameter("username")
                         .passwordParameter("password");

                 })
                 .authenticationProvider(authProvider());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(encoder());

        return authenticationProvider;
    }

}
