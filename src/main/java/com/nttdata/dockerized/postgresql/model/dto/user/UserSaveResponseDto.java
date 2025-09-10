package com.nttdata.dockerized.postgresql.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSaveResponseDto {

    private Long userId;

    private String name;

    private LocalDate fechaRegistro;

    private String email;
}
