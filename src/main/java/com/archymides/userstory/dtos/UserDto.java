package com.archymides.userstory.dtos;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @Email(message = "Email should be of valid format")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Role role;

    public UserDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}

