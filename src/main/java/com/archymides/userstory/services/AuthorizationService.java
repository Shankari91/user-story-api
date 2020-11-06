package com.archymides.userstory.services;

import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AuthorizationService")
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
    }
}
