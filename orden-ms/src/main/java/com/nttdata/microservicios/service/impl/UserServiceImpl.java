package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.exception.BadRequestException;
import com.nttdata.microservicios.exception.InternalServerErrorException;
import com.nttdata.microservicios.exception.ResourceNotFoundException;
import com.nttdata.microservicios.mapper.UserMapper;
import com.nttdata.microservicios.model.dto.user.UserDto;
import com.nttdata.microservicios.model.dto.user.UserSaveRequestDto;
import com.nttdata.microservicios.model.dto.user.UserSaveResponseDto;
import com.nttdata.microservicios.model.dto.user.UserUpdateDto;
import com.nttdata.microservicios.model.entity.User;
import com.nttdata.microservicios.repository.UserRepository;
import com.nttdata.microservicios.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> listAll() {
        List<User> usuarios = (List<User>) userRepository.findAll();
        return userMapper.toUsersDto(usuarios);
    }

    @Override
    public UserDto findById(Long id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        User usuario = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el usuario con el id: "+id)
        );
        return userMapper.toUserDto(usuario);
    }

    @Override
    public UserDto findByEmail(String email) {
        if(email == null || email.isBlank()) {
            throw new BadRequestException("Colocar el email en el parámetro");
        }
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el usuario con el email: "+ email)
        );
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> findByActive() {
        List<User> usuarios = userRepository.findByActive(true);
        return userMapper.toUsersDto(usuarios);
    }

    @Override
    public UserSaveResponseDto save(UserSaveRequestDto userSaveRequestDto) {
        if (userSaveRequestDto == null) throw new BadRequestException("El request no puede ser nulo");
        try {
            User user = userMapper.toUserSaveRequest(userSaveRequestDto);
            user.setActive(Boolean.TRUE);
            return userMapper.toUserSaveResponseDto( userRepository.save(user) );
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar un usuario");
        }
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        return userRepository.findById(id)
                .map( usuario -> {
                    userMapper.updateEntityFromDto(userUpdateDto, usuario);
                    return userMapper.toUserDto(userRepository.save(usuario));
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró el usuario con el id: "+id)
                );
    }

    @Override
    public void delete(Long id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el usuario con id: "+ id)
        );
        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException exception) {
            throw new BadRequestException("No se puede eliminar el usuario porque tienes registros asociados");
        }  catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al eliminar el usuario");
        }
    }
}
