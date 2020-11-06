package com.archymides.userstory.dtos;
import lombok.Getter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {

    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @Email(message = "Email should be of valid format")
    private String email;
    private String password;
    private Role role;
}

