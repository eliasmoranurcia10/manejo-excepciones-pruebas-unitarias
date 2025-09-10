package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.dto.user.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.user.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.user.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.user.UserUpdateDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return INSTANCE.map(userService.listAll());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return INSTANCE.map(userService.findById(id));
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(
            @PathVariable String email
    ) {
        return INSTANCE.map( userService.findByEmail(email));
    }

    @GetMapping("/activeuser")
    public List<UserDto> getActiveUsers() {
        return INSTANCE.map(userService.findByActive(true));
    }

    @PostMapping
    public UserSaveResponseDto save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto) {
        return INSTANCE.toUserSaveResponseDto(userService.save(INSTANCE.toEntity(userSaveRequestDto)));
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        User user = userService.findById(id);
        INSTANCE.updateEntityFromDto(userUpdateDto, user);
        return INSTANCE.map(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if( userService.findById(id) == null ) throw new ResourceNotFoundException("No se encontró el usuario con el id: "+id);
        userService.delete(id);
    }
}
