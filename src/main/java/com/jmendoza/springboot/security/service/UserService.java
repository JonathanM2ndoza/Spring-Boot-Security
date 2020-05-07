package com.jmendoza.springboot.security.service;

import com.jmendoza.springboot.security.constants.UserConstanst;
import com.jmendoza.springboot.security.exception.GlobalException;
import com.jmendoza.springboot.security.exception.ResourceNotFoundException;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.repository.UserRepository;
import com.jmendoza.springboot.security.util.SecurityUtil;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {

        List<User> userList = userRepository.findAll();

        return userList.stream().map(user -> {
            user.setPassword(null);
            return user;
        }).collect(Collectors.toList());
    }

    public User getUser(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(UserConstanst.USER_NOT_FOUND + userId));
    }

    public User createUser(User user) throws GlobalException {
        if (userRepository.existsByEmail(user.getEmail()).booleanValue())
            throw new GlobalException("This email is already registered");

        user.setPassword(securityUtil.passwordEncoder(user.getPassword()));
        userRepository.save(user);
        User userResult = SerializationUtils.clone(user);
        userResult.setPassword(null);
        return userResult;
    }

    public void updateUser(Long userId, User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        userRepository.save(user);
    }

    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
        userRepository.delete(user);
    }
}
