package com.jmendoza.springboot.security.controller;

import com.jmendoza.springboot.security.exception.GlobalException;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.model.request.SignInRequest;
import com.jmendoza.springboot.security.model.request.SignUpRequest;
import com.jmendoza.springboot.security.model.response.SignInResponse;
import com.jmendoza.springboot.security.model.response.SignUpResponse;
import com.jmendoza.springboot.security.service.AuthenticateService;
import com.jmendoza.springboot.security.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = convertToDto(userService.createUser(convertToEntity(signUpRequest)));
        return ResponseEntity.created(URI.create("/users/" + signUpResponse.getId())).body(signUpResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> createToken(@Valid @RequestBody SignInRequest signInRequest) throws GlobalException {
        SignInResponse signInResponse = authenticateService.createToken(signInRequest.getEmail(), signInRequest.getPassword());
        return ResponseEntity.ok().body(signInResponse);
    }

    private SignUpResponse convertToDto(User user) {
        return modelMapper.map(user, SignUpResponse.class);
    }

    private User convertToEntity(SignUpRequest signUpRequest) {
        return modelMapper.map(signUpRequest, User.class);
    }
}
