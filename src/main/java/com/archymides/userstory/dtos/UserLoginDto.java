package com.archymides.userstory.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto extends UserDto {
    private String token;
    private Long id;
}
