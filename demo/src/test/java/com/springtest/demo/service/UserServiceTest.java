package com.springtest.demo.service;

import com.springtest.demo.Repository.UserRepository;
import com.springtest.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Test
    void testSaveUser() {
        UserRepository userRepository = spy(UserRepository.class);
        UserService userService = new UserService(userRepository);


        // Assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        // Act
        userService.saveUser("1", "John Doe");

        verify(userRepository).save(userCaptor.capture()); // Captures the User argument passed to save

        User capturedUser = userCaptor.getValue();
        assertEquals("1", capturedUser.getUserId());
        assertEquals("John Doe", capturedUser.getName());
    }
}