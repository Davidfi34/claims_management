package com.claims_management.user.Dto;

import com.claims_management.user.model.User;

public record UserResponse(Long id, String firstname, String lastname) {
    public UserResponse(User user){
        this(user.getId(),user.getFirstname(),user.getLastname());
    }
}
