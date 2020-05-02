package com.jmendoza.springboot.security.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public ResponseEntity<String> createToken(@RequestBody String requestBody) throws Exception {

        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
        String token = securityService.createToken(jsonObject.get("email").getAsString(), jsonObject.get("password").getAsString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "SecurityController");

        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("token", token);
        return ResponseEntity.ok().headers(headers).body(jsonObject1.toString());
    }

}
