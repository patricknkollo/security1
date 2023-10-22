/*
package com.security.example.secu.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeaders = request.getHeader("Authorization");
        Principal currentUser = request.getUserPrincipal();
        String jwt;
        String userEmail;
        if (authHeaders==null || !authHeaders.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeaders.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        //SecurityContextHolder.getContext().getAuthentication()==null   signifie que le user n'a pas encore été authetifié.
        // dans ce cas il doit dabord être authentifier et donc on verifie si le user est bien existant et s'il a deja été authentifié
        if (userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //le user etant bien existant nous pouvons le charger (prendre) de la base de données
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            //si le token est toujours valid...
            if (jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
*/
