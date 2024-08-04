package com.claims_management.user.Dto;

import com.claims_management.user.model.User;

public record UserResponse(Long id, String username) {
    public UserResponse(User user){
        this(user.getId(),user.getUsername());
    }
}
