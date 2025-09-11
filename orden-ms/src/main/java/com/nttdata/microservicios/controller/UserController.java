package com.nttdata.microservicios.controller;

import com.nttdata.microservicios.model.dto.user.UserDto;
import com.nttdata.microservicios.model.dto.user.UserSaveRequestDto;
import com.nttdata.microservicios.model.dto.user.UserSaveResponseDto;
import com.nttdata.microservicios.model.dto.user.UserUpdateDto;
import com.nttdata.microservicios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/search")
    public UserDto getUserByEmail(
            @RequestParam String email
    ) {
        return userService.findByEmail(email);
    }

    @GetMapping("/active-users")
    public List<UserDto> getActiveUsers() {
        return userService.findByActive();
    }

    @PostMapping
    public UserSaveResponseDto save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userService.update(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
