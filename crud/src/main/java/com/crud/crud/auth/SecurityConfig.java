package com.crud.crud.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .csrf(csrf ->csrf.disable())
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    
    @Bean
    /*
      Can't do this - BCryptPasswordEncoder is from a library, doesn't have @Component
      w brdo menf3sh a3mlha autowired 3shan spring msh hy3rf how to create it, so we containrize it in a bean, export it on runtime
      so with this way i tell spring how to create it and how to inject it in the controller.
      WRBNA THIS IS MAGIC
      notes -> library needs bean, 8er kda you can define a component normally
     */
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
