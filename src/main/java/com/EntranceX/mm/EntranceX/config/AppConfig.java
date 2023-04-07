package com.EntranceX.mm.EntranceX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//@EnableWebSecurity
@Configuration
public class AppConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
//        httpSecurity
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/about", "/contact", "/help", "/privacypolicy", "/termsandconditions").permitAll()
//                .requestMatchers("user/**").hasRole("USER")
//                .requestMatchers("admin/**").hasRole("ADMIN")
//                .requestMatchers("organizer/**").hasRole("ORGANIZER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin((form) -> form
//                        .loginPage("/login").permitAll()
//                        .defaultSuccessUrl("/dashboard").permitAll())
//                .logout()
//                    .logoutUrl("/logout").permitAll()
//                .and()
//                .httpBasic();
//        return httpSecurity.build();
//    }
//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//        }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
