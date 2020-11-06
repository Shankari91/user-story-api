package com.archymides.userstory.controllers;

import com.archymides.userstory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@RestController
@Validated
public class LoginController {

    @Autowired
    private UserService userService;

     @ResponseStatus(HttpStatus.OK)
     public void registerUser(@Valid  @RequestBody User user) {
         userService.saveUser(user);
     }

}






