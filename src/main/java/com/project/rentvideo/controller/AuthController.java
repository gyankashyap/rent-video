package com.project.rentvideo.controller;

import com.project.rentvideo.dto.UserRegistrationRequest;
import com.project.rentvideo.dto.UserResponse;
import com.project.rentvideo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        UserResponse userResponse = userService.registerUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        UserResponse userResponse = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(userResponse);
    }
}
