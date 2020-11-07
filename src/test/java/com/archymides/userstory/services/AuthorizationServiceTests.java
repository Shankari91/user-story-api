package com.archymides.userstory.services;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.archymides.userstory.Exceptions.UnauthorizedException;
import com.archymides.userstory.dtos.LoginDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.dtos.UserLoginDto;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.mappers.ModelMapperService;
import com.archymides.userstory.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorizationServiceTests {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private AuthorizationService authorizationService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveUserObjectToRepository() {

        UserDto userDto = new UserDto();
        User mappedUser = new User();
        when(modelMapperService.map(userDto)).thenReturn(mappedUser);
        when(userRepository.save(mappedUser)).thenReturn(new User());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        authorizationService.registerUser(userDto);

        verify(userRepository).save(mappedUser);
    }

    @Test
    public void shouldGetUserFromRepository() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        authorizationService.getUser(userId);

        verify(userRepository).findById(userId);
    }

    @Test
    public void shouldVerifyUserNameAndPasswordForLogin() {
        LoginDto loginDto = new LoginDto();
        String email = "email@email";
        loginDto.setEmail(email);
        loginDto.setPassword("test");
        User user = new User();
        String token = "token";
        user.setPassword(BCrypt.withDefaults().hashToString(12, "test".toCharArray()));
        when(userRepository.findByemail(email)).thenReturn(user);
        UserLoginDto mappedLoginDto = new UserLoginDto();
        when(modelMapperService.map(user, token)).thenReturn(mappedLoginDto);
        when(jwtService.generateJwtToken(user)).thenReturn(token);

        UserLoginDto userLoginDto = authorizationService.handleLogin(loginDto);

        verify(userRepository).findByemail(email);
        assertEquals(userLoginDto, mappedLoginDto);
    }

    @Test(expected = UnauthorizedException.class)
    public void ShouldThrowExceptionWhenUserIsEmptyForLogin() throws UnauthorizedException {
        LoginDto loginDto = new LoginDto();
        String email = "email@email";
        loginDto.setEmail(email);
        when(userRepository.findByemail(email)).thenReturn(null);

        authorizationService.handleLogin(loginDto);
    }
}
