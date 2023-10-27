

package com.security.example.secu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private CustomUserDetailService customUserDetailService;

    // Bean qui permet de definir quel type d'autorisation j'utilise pour l'application; ici par exemple j'utilise Basic Auth (httpBasic)
    @Bean
    public SecurityFilterChain configureDanVega(HttpSecurity http) throws Exception {
        http .csrf(csrf -> csrf.disable())
                .authorizeRequests( auth ->{
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/users")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/api/auth/**")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users/**")).hasAnyRole("ADMIN");
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users/userid/**")).hasAnyRole("USER");
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users/name/**")).hasAnyRole("ADMIN");
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("api/Customer/**")).hasAnyRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }
}

