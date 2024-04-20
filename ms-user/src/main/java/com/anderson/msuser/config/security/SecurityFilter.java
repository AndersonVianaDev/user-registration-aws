package com.anderson.msuser.config.security;

import com.anderson.msuser.adapters.user.repositories.JpaUserRepository;
import com.anderson.msuser.core.services.TokenService;
import com.anderson.msuser.shared.exceptions.NotFoundException;
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
import java.util.logging.Logger;

import static com.anderson.msuser.shared.constants.ExceptionConstants.NOT_FOUND;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final JpaUserRepository repository;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public SecurityFilter(TokenService tokenService, JpaUserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        if(token != null) {
            logger.fine("token Internal Filter: " + token);
            UUID id = tokenService.validateToken(token);
            UserDetails user = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));

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
