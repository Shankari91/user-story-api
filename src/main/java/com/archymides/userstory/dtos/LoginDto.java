package com.archymides.userstory.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {

    @Email(message = "Email should be of valid format")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
