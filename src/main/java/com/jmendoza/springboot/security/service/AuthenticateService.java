package com.jmendoza.springboot.security.service;

import com.jmendoza.springboot.security.exception.GlobalException;
import com.jmendoza.springboot.security.model.response.SignInResponse;
import com.jmendoza.springboot.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public SignInResponse createToken(String email, String password) throws GlobalException {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new GlobalException("Incorrect username or password", e);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SignInResponse security = new SignInResponse();
        security.setToken(jwtUtil.generateToken(userDetails));
        return security;
    }
}
