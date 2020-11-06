package com.archymides.userstory.dtos;

import com.archymides.userstory.entities.User;
import lombok.Getter;

@Getter
public class UserLoginDto extends UserDto {
    private String token;
    private Long id;

    public UserLoginDto(User user, String token) {
        super(user);
        this.id = user.getId();
        this.token = token;
    }
}
