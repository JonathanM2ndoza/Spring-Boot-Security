package com.jmendoza.springboot.security.controller;

import com.jmendoza.springboot.security.model.Security;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @PostMapping("/login")
    public ResponseEntity<Security> createToken(@RequestBody User user) throws Exception {
        Security security = securityService.createToken(user.getEmail(), user.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "SecurityController");

        return ResponseEntity.ok().headers(headers).body(security);
    }

}
