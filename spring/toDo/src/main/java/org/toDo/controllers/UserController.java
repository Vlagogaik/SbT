package org.toDo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.toDo.entity.User;
import org.toDo.entity.UserRegistrationRequest;
import org.toDo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String username = request.getUsername();
        logger.info("Received registration request for email: {}", email);
        User registeredUser = userService.register(email, password, username);
        logger.info("Registered user with email: {}", registeredUser.getEmail());

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserRegistrationRequest userRequest) {
        User authenticatedUser = userService.login(userRequest.getUsername(), userRequest.getPassword());
        return ResponseEntity.ok(authenticatedUser);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}

