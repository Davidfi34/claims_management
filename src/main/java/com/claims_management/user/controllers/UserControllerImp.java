package com.claims_management.user.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.user.Dto.UpdateUser;
import com.claims_management.user.Dto.UserRequest;
import com.claims_management.user.Dto.UserResponse;
import com.claims_management.user.service.UserServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.user.constants.UserConstants.REQUEST_USER;
import static com.claims_management.commons.constants.GlobalApiConstant.*;

@RestController
@RequestMapping(REQUEST_USER)
public class UserControllerImp extends GenericRestController implements UserController {

    private final UserServiceImp userServiceImp;

    public UserControllerImp( UserServiceImp userServiceImp){
        this.userServiceImp = userServiceImp;
    }


    @Override
    public ResponseEntity<CustomResponse> createUser(UserRequest userRequest) {
        return ok(userServiceImp.saveUser(userRequest),CREATED,REQUEST_USER);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllUsers(int numberPage) {
        return ok(userServiceImp.getAllUser(numberPage),null, REQUEST_USER);
    }

    @Override
    public ResponseEntity<CustomResponse> getUserById(Long id) {
        UserResponse userResponse = userServiceImp.getUserById(id);
        return ok(userResponse,null,REQUEST_USER);
    }

    @Override
    public ResponseEntity<CustomResponse> updateUser(UpdateUser updateUser) {
        UserResponse userResponse = userServiceImp.updateUser(updateUser);
        return ok(userResponse,null,REQUEST_USER);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteUserById(Long id) {
        userServiceImp.deleteUser(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_USER);
    }
}
