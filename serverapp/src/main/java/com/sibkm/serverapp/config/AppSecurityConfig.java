package com.sibkm.serverapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;

import com.sibkm.serverapp.service.AppUserDetailService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {

  private AppUserDetailService appUserDetailService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/registration")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/login")
            .permitAll()
            .anyRequest()
            .authenticated()
        )
         .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        )
        // .formLogin(form -> form
        //     .loginPage("/login")
        //     .defaultSuccessUrl("/index", true)
        //     .permitAll()
        // )
        // .logout(logout -> logout
        //     .logoutUrl("/logout")
        //     .logoutSuccessUrl("/login?logout=true")
        //     .permitAll()
        // )
        // .sessionManagement(session -> session
        //     .sessionFixation().migrateSession()
        //     .maximumSessions(1)
        // )
        .userDetailsService(appUserDetailService)
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

}