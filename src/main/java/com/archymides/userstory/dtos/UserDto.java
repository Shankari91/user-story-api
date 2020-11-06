package com.archymides.userstory.dtos;
import lombok.Getter;
import javax.validation.constraints.Email;

@Getter
public class UserDto {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private Role role;
}

