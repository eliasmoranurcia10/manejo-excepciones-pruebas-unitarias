package com.nttdata.dockerized.postgresql.service.impl;
import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.mapper.UserMapper;
import com.nttdata.dockerized.postgresql.model.dto.user.UserDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Integra Mockito con JUnit5
//@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    // Creamos los objetos simulados
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    // Objeto al que se inyectarán los Mocks
    @InjectMocks
    private UserServiceImpl userService;

    //Datos de prueba
    private User user;
    private UserDto userDto;

    //Inicialización de cada test
    @BeforeEach
    void setUp() {
        // Inicializa los @Mocks y @InjectMocks
        MockitoAnnotations.openMocks(this);

        // Creacion del user de prueba y su Dto equivalente
        user = new User();
        user.setIdUser(1L);
        user.setName("Mario");
        user.setFechaRegistro(LocalDate.now());
        user.setEmail("mario@test.com");
        user.setActive(true);

        userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setName("Mario");
        userDto.setFechaRegistro(LocalDate.now());
        userDto.setEmail("mario@test.com");
        userDto.setStatus("Active");

    }

    @Test
    void testListAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toUsersDto(List.of(user))).thenReturn(List.of(userDto));

        List<UserDto> result = userService.listAll();

        assertNotNull(result); // Valida que el resultado no sea nulo
        assertEquals(1, result.size());
        assertEquals("Mario", result.get(0).getName() );
        verify(userRepository, times(1)).findAll(); //Verifica que el repositorio fue llamado una vez
    }


    /*@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setIdUser(1L);
        user.setEmail("test@email.com");
        user.setActive(true);
    }*/

    /*@Test
    void testListAll_Success() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> result = userService.listAll();

        assertEquals(1, result.size());
        assertEquals("test@email.com", result.get(0).getEmail());
        verify(userRepository, times(1)).findAll();
    }*/

    /*@Test
    void testSave_Success() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.save(user);

        assertTrue(result.getActive());
        assertEquals("test@email.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }*/

    /*@Test
    void testUpdate_Success() {
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.update(user.getIdUser(), user);

        assertNotNull(result);
        assertEquals("test@email.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }*/

    /*@Test
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
    }*/
}