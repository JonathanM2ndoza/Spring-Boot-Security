package com.jmendoza.springboot.security.controller;

import com.jmendoza.springboot.security.exception.ResourceNotFoundException;
import com.jmendoza.springboot.security.model.User;
import com.jmendoza.springboot.security.model.request.UpdateUserRequest;
import com.jmendoza.springboot.security.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataType = "String", example = "access_token")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.getUsers();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController");
        return ResponseEntity.ok().headers(headers).body(userList);
    }

    @GetMapping("/users/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataType = "String", example = "access_token")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userService.getUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataType = "String", example = "access_token")
    public ResponseEntity updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody UpdateUserRequest updateUserRequest) throws ResourceNotFoundException {
        userService.updateUser(userId, convertToEntity(updateUserRequest));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataType = "String", example = "access_token")
    public ResponseEntity deleteUser(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    private User convertToEntity(UpdateUserRequest updateUserRequest) {
        return modelMapper.map(updateUserRequest, User.class);
    }
}
