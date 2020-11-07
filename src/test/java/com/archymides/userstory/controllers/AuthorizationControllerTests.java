package com.archymides.userstory.controllers;

import com.archymides.userstory.dtos.LoginDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.dtos.UserLoginDto;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.services.AuthorizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorizationControllerTests {

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private AuthorizationController authorizationController;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCallAuthorisationServiceForRegistering(){

        UserDto userDto = new UserDto();
        doNothing().when(authorizationService).registerUser(userDto);

        authorizationController.registerUser(userDto);

        verify(authorizationService).registerUser(userDto);

    }

    @Test
    public void shouldCallHandleLoginForlogin(){

        LoginDto loginDto = new LoginDto();
        UserLoginDto userLoginDto = mock(UserLoginDto.class);
        when(authorizationService.handleLogin(loginDto)).thenReturn(userLoginDto);

        UserLoginDto receivedLogin = authorizationController.login(loginDto);

        assertEquals(receivedLogin, userLoginDto);
        verify(authorizationService).handleLogin(loginDto);

    }
}
