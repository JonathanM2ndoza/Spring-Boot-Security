package com.jmendoza.springboot.security.service;

import com.jmendoza.springboot.security.constants.UserConstanst;
import com.jmendoza.springboot.security.exception.ResourceNotFoundException;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.repository.UserRepository;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(UserConstanst.USER_NOT_FOUND + userId));
    }

    public User createUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        User userResult = SerializationUtils.clone(user);
        userResult.setPassword(null);
        return userResult;
    }

    public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
        userRepository.delete(user);
    }
}
