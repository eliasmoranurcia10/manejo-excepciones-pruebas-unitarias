package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el usuario con el id: "+id)
        );
    }

    @Override
    public User save(User user) {
        user.setActive(Boolean.TRUE);
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar un usuario");
        }
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        if(email.isBlank()) {
            throw new BadRequestException("Colocar el email en el parámetro");
        }
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el usuario con el email: "+ email);
        }
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> findByActive(Boolean active) {
        return userRepository.findByActive(active);
    }
}
