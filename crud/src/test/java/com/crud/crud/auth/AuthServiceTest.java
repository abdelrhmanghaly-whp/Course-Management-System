package com.crud.crud.auth;

import com.crud.crud.model.User;
import com.crud.crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;
    private AuthRequest testRequest;
    private User testUser;

    @BeforeEach
    void setUp(){
        testRequest = new AuthRequest("gholyo","pass123");
        testUser = new User("gholyo","pass123","gholoy@gmail.com");
    }

    @Test 
    void register_WhenNewUser_ShouldReturnAuthResponse(){
        when(userRepository.findByUsername("gholyo")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("pass123")).thenReturn("encodedPass");
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(jwtService.generateToken("gholyo")).thenReturn("token");

        AuthResponse res= authService.register(testRequest);

        assertNotNull(res);
        assertEquals("gholyo",res.getUsername());
        assertEquals("token",res.getToken());
        verify(userRepository,times(1)).findByUsername("gholyo");
    }

    @Test
    void register_WhenUsernameExists_ShouldThrowException(){

        when(userRepository.findByUsername("gholyo")).thenReturn(Optional.of(testUser));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.register(testRequest));

        assertTrue(ex.getMessage().contains("Username already exists"));
        verify(userRepository,never()).save(any(User.class));
    }

    @Test
    void login_WhenValidCredentials_ShouldReturnAuthResponse(){

        when(userRepository.findByUsername("gholyo")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("pass123",testUser.getPassword())).thenReturn(true);
        when(jwtService.generateToken("gholyo")).thenReturn("token");

        AuthResponse response=authService.login(testRequest);

        assertNotNull(response);
        assertEquals("gholyo",response.getUsername());
        assertEquals("token",response.getToken());

    }
    
    @Test
    void login_WhenUserNotFound_ShouldThrowException(){
        when(userRepository.findByUsername("gholyo")).thenReturn(Optional.empty());
        RuntimeException ex=assertThrows(RuntimeException.class, () -> authService.login(testRequest));

        assertTrue(ex.getMessage().contains("Invalid username or password"));
    }

    @Test
    void login_WhenWrongPassword_ShouldThrowException(){

        when(userRepository.findByUsername("gholyo")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("pass123",testUser.getPassword())).thenReturn(false);

        RuntimeException ex=assertThrows(RuntimeException.class, () -> authService.login(testRequest));

        assertTrue(ex.getMessage().contains("Invalid username or password"));
    }



}
