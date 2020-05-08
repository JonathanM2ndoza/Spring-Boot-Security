package com.jmendoza.springboot.security.service;

import com.jmendoza.springboot.security.constants.UserConstanst;
import com.jmendoza.springboot.security.exception.ResourceNotFoundException;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userOptional;
        UserDetails userDetails = null;
        try {
            userOptional = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(UserConstanst.USER_NOT_FOUND + email)));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .collect(Collectors.toList()));
            }
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(UserConstanst.USER_NOT_FOUND + email, e);
        }
        return userDetails;
    }
}
