package com.demo.config;

import com.demo.entity.AppUser;
import com.demo.repository.AppUserRepository;
import com.demo.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTResponseFilter extends OncePerRequestFilter {

    private JWTService jwtService;

    private AppUserRepository userRepository;

    public JWTResponseFilter(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")){
           String token = tokenHeader.substring(8,tokenHeader.length()-1);
            String username = jwtService.getUserName(token);
            Optional<AppUser> opUser = userRepository.findByUsername(username);
            if(opUser.isPresent()){
                AppUser appUser = opUser.get();
                //To track current user logged in
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getUserRole())));
                authentication.setDetails(new WebAuthenticationDetails(request)); // setting session id for user logged in
                SecurityContextHolder.getContext().setAuthentication(authentication); //session id is stored
            }

        }
        filterChain.doFilter(request,response);
    }
}
