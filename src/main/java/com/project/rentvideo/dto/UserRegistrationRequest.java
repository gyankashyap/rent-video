package com.project.rentvideo.dto;

import com.project.rentvideo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
