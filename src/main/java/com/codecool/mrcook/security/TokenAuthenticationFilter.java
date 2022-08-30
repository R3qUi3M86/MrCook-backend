package com.codecool.mrcook.security;

import com.codecool.mrcook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(header) || (StringUtils.hasText(header) && !header.startsWith("Bearer "))){
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        if (!tokenProvider.validateToken(token)){
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userRepository
                .findByUsername(tokenProvider.getUsernameFromToken(token))
                .orElse(null);

        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails == null ?
                                List.of() : userDetails.getAuthorities()
        );

        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }
}