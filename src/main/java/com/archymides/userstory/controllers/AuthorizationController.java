package com.archymides.userstory.controllers;

import com.archymides.userstory.dtos.LoginDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.dtos.UserLoginDto;
import com.archymides.userstory.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@Validated
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserDto userDto) {
        authorizationService.registerUser(userDto);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginDto login(@Valid @RequestBody LoginDto loginDto) {
        return authorizationService.handleLogin(loginDto);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName(Authentication authentication, Principal principal) {
        System.out.println(authentication.getName());
        System.out.println("-----------------");
        System.out.println(principal.getName());
        return principal.getName();
    }

}






