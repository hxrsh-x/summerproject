package com.kapil.NgoEventManager.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(JwtConstant.JWT_Header);

        System.out.println("JWT Token received: " + (jwt != null ? "Yes" : "No"));

        if(jwt!=null){

            try {
                String email = JwtProvider.getEmailFromToken(jwt);
                if (email != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Authentication set in SecurityContext for email: " + email);
                } else {
                    System.out.println("Failed to extract email from token");
                }
            } catch (Exception e) {
                System.out.println("Error processing JWT token: " + e.getMessage());
            }
        }else {
            System.out.println("No JWT token found in request headers");
        }
        filterChain.doFilter(request,response);
    }
}
