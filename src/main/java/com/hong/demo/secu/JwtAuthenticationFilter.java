package com.hong.demo.secu; 

import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import com.hong.demo.secu.service.JwtService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    static  final String PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter(
        JwtService jwtService, 
        UserDetailsService userDetailsService,
        // @Qualifier("handlerExceptionResolver") 
        HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService; 
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                FilterChain filterChain) throws ServletException, IOException {
        
        // Get token from the request Authorization header
        // String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String authHeader = request.getHeader("Authorization");

        // request to me ?
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            // final String jwt = authHeader.substring(7);
            final String jwt = authHeader.replace(PREFIX, "");

            // Verify token and get username
            String username = jwtService.getAuthUser(jwt);
            
            // reset authenticated user if necessary
            Authentication authen = SecurityContextHolder.getContext().getAuthentication();
            if (authen == null || !authen.getName().equals(username)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                authen = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        userDetails.getAuthorities() 
                );
                SecurityContextHolder.getContext().setAuthentication(authen);
            }

            // next filter
            filterChain.doFilter(request, response);

        } catch (Exception ex){
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }

    }
}
