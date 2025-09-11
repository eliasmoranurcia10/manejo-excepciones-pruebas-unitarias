package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.user.UserDto;
import com.nttdata.microservicios.model.dto.user.UserSaveRequestDto;
import com.nttdata.microservicios.model.dto.user.UserSaveResponseDto;
import com.nttdata.microservicios.model.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {

    List<UserDto> listAll();

    UserDto findById(Long id);

    UserDto findByEmail(String email);

    List<UserDto> findByActive();

    UserSaveResponseDto save(UserSaveRequestDto userSaveRequestDto);

    UserDto update(Long id, UserUpdateDto userUpdateDto);

    void delete(Long id);

}
