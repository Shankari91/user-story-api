package com.archymides.userstory.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.archymides.userstory.Exceptions.UnauthorizedException;
import com.archymides.userstory.dtos.LoginDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.dtos.UserLoginDto;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AuthorizationService")
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public void registerUser(UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
    }

    public UserLoginDto handleLogin(LoginDto loginDto) {
        User user = userRepository.findByemail(loginDto.getEmail());
        if (user == null || !BCrypt.verifyer().verify(loginDto.getPassword().toCharArray(), user.getPassword()).verified) {
            throw new UnauthorizedException();
        }
        String token = jwtService.generateJwtToken(user);

        //Move this logic to mapper
        UserLoginDto userLoginDto = new UserLoginDto(user, token);
        return userLoginDto;
    }
}
