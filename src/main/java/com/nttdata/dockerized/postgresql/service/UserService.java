package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    User findById(Long id);

    User save(User user);

    User update(User user);

    void delete(Long id);

    User findByEmail(String email);

    List<User> findByActive(Boolean active);
}
