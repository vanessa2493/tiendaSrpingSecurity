package com.example.tienda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(5);}

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails usuario1 = User.builder()
                .username("vane")
                .password(passwordEncoder().encode("contrasena"))
                .roles(UserRole.ADMIN.name())
                .build();

        UserDetails usuario2 = User.builder()
                .username("vito")
                .password(passwordEncoder().encode("contrasena"))
                .roles(UserRole.CLIENTE.name())
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }
}
