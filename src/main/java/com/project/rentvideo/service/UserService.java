package com.project.rentvideo.service;

import com.project.rentvideo.dto.UserRegistrationRequest;
import com.project.rentvideo.dto.UserResponse;
import com.project.rentvideo.model.Role;
import com.project.rentvideo.model.User;
import com.project.rentvideo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRegistrationRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Set default role to CUSTOMER if not specified
        Role role = (request.getRole() != null) ? request.getRole() : Role.CUSTOMER;

        // Create user entity
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(role)
                .build();

        // Save user
        User savedUser = userRepository.save(user);

        // Return DTO
        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .role(savedUser.getRole())
                .build();
    }
    
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }
}
