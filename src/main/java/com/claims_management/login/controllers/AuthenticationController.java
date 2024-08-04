package com.claims_management.login.controllers;

import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.user.Dto.UserAuthenticationData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {

    @PostMapping
    ResponseEntity<CustomResponse> authenticationUser(@RequestBody @Valid UserAuthenticationData userAuthenticationData );
}
