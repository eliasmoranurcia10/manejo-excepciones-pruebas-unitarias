package com.nttdata.microservicios.repository;

import com.nttdata.microservicios.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByActive(Boolean active);
}
