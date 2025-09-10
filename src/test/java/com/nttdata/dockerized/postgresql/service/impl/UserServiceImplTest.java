package com.nttdata.dockerized.postgresql.service.impl;
import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@email.com");
        user.setActive(true);
    }

    @Test
    void testListAll_Success() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> result = userService.listAll();

        assertEquals(1, result.size());
        assertEquals("test@email.com", result.get(0).getEmail());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSave_Success() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.save(user);

        assertTrue(result.getActive());
        assertEquals("test@email.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdate_Success() {
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.update(user);

        assertNotNull(result);
        assertEquals("test@email.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDelete_Success() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(99L));
        verify(userRepository, times(1)).findById(99L);
    }

    @Test
    void testFindByEmail_BadRequest() {
        assertThrows(BadRequestException.class, () -> userService.findByEmail(" "));
    }
}