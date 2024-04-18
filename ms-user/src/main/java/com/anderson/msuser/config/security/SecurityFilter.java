package com.anderson.msuser.config.security;

import com.anderson.msuser.adapters.user.repositories.JpaUserRepository;
import com.anderson.msuser.core.user.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final JpaUserRepository repository;

    public SecurityFilter(TokenService tokenService, JpaUserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        if(token != null) {
            UUID id = tokenService.validateToken(token);
            UserDetails user = repository.findById(id).orElseThrow(() -> new RuntimeException("User with email not found."));

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return Objects.isNull(authHeader) ? null : authHeader.replace("Bearer ", "");
    }
}
