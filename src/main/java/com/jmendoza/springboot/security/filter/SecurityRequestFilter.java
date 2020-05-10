package com.jmendoza.springboot.security.filter;

import com.jmendoza.springboot.security.service.SecurityUserDetailsService;
import com.jmendoza.springboot.security.util.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityRequestFilter extends OncePerRequestFilter {

    private static final Logger loggerFilter = LogManager.getLogger(SecurityRequestFilter.class);

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String jwt = request.getHeader("Authorization");
            if (jwt != null) {
                String username = jwtUtil.extractUsername(jwt);
                UserDetails userDetails = securityUserDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails).booleanValue()) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            loggerFilter.error(e.toString());
        } finally {
            chain.doFilter(request, response);
        }
    }
}
