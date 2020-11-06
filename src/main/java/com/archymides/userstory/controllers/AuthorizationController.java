package com.archymides.userstory.controllers;

import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

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

}






