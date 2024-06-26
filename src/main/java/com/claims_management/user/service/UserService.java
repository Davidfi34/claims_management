package com.claims_management.user.service;

import com.claims_management.user.Dto.UpdateUser;
import com.claims_management.user.Dto.UserRequest;
import com.claims_management.user.Dto.UserResponse;
import org.springframework.data.domain.Page;


public interface UserService {

    public UserResponse saveUser(UserRequest userRequest);
    public UserResponse getUserById(Long id);
    public Page<UserResponse> getAllUser(int numberPage);
    public UserResponse updateUser(UpdateUser updateUser);
    public void deleteUser(Long id);
}
