package com.claims_management.login.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.infra.security.JwtTokenData;
import com.claims_management.infra.security.TokenService;
import com.claims_management.user.Dto.UserAuthenticationData;
import com.claims_management.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.login.constants.loginConstants.LOGIN;


@RestController
@RequestMapping(LOGIN)
public class AuthenticationControllerImp extends GenericRestController implements AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthenticationControllerImp(AuthenticationManager authenticationManager,
                                       TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @Override
    public ResponseEntity authenticationUser( UserAuthenticationData userAuthenticationData ){
        Authentication authToken = new UsernamePasswordAuthenticationToken( userAuthenticationData.username(),
                userAuthenticationData.password());

        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ok(new JwtTokenData(JwtToken),null,LOGIN);
    }

}