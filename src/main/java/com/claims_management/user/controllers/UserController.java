package com.claims_management.user.controllers;

import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.user.Dto.UpdateUser;
import com.claims_management.user.Dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface UserController {


    @PostMapping
    ResponseEntity<CustomResponse> createUser(@RequestBody UserRequest userRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllUsers(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getUserById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateUser(@RequestBody UpdateUser updateUser);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteUserById(@PathVariable Long id);

}
