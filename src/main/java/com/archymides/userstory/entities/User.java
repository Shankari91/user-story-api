package com.archymides.userstory.entities;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.archymides.userstory.enums.Role;
import com.archymides.userstory.dtos.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
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


    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}
