

package com.security.example.secu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    // Bean qui permet de definir quel type d'autorisation j'utilise pour l'application; ici par exemple j'utilise Basic Auth (httpBasic)
    @Bean
    public SecurityFilterChain configureDanVega(HttpSecurity http) throws Exception {
        http .csrf(csrf -> csrf.disable())
                .authorizeRequests( auth ->{
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users/**")).hasAnyRole("ADMIN");
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users/userid/**")).hasAnyRole("USER");
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/users/name/**")).hasAnyRole("ADMIN");
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    //Below is an example configuration without WebSecurityConfigurerAdapter that configures an in-memory user store with a single user:
    //ici je definis les utilisateurs à même de pouvoir acceder à l'application
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("pnkollo17")
                .password("17081989N*")
                .roles("ADMIN")
                //.authorities("admin")
                .build();

        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("enkollo02")
                .password("02082023N*")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(List.of( user, user1));
    }
}

