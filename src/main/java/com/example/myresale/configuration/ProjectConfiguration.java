package com.example.myresale.configuration;

import com.example.myresale.services.UserInfoDetailsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CTRL + ALT + B -> посмотреть доступные реализации

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class ProjectConfiguration implements WebMvcConfigurer {
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
                 .csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(request -> {request
                         .requestMatchers("/login", "/registration", "/items", "/logout").permitAll()
                         .requestMatchers("/css/**", "/images/**").permitAll()
                         .requestMatchers("/items/{id}").hasRole("USER")
                         .requestMatchers(HttpMethod.DELETE, "/items").hasRole("ADMIN");
                 })
                 .formLogin(form ->{form
                         .loginPage("/login")
                         .loginProcessingUrl("/login")
                         .defaultSuccessUrl("/items", true)
                         .failureForwardUrl("/login?error=true")
                         .usernameParameter("username")
                         .passwordParameter("password")
                         .permitAll();

                 })
                 .logout(logoutConfigurer -> logoutConfigurer
                         .logoutUrl("/logout")
                         .logoutSuccessUrl("/login")
                         .permitAll())
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }
}
