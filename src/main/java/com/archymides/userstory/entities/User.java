package com.archymides.userstory.entities;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.archymides.userstory.dtos.Role;
import com.archymides.userstory.dtos.UserDto;

import javax.persistence.*;

@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    //TOOD move this to modelmapper
    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.role = userDto.getRole();
        this.password = BCrypt.withDefaults().hashToString(12, userDto.getPassword().toCharArray());
    }
}
