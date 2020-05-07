package com.jmendoza.springboot.security.controller;

import com.jmendoza.springboot.security.model.Security;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.service.AuthenticateService;
import com.jmendoza.springboot.security.service.UserService;
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

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User userCreated = userService.createUser(user);
        return ResponseEntity.created(URI.create("/users/" + userCreated.getId())).body(userCreated);
    }

    @PostMapping("/signin")
    public ResponseEntity<Security> createToken(@RequestBody User user) throws Exception {
        Security security = authenticateService.createToken(user.getEmail(), user.getPassword());
        return ResponseEntity.ok().body(security);
    }

}
