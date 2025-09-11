package com.nttdata.microservicios.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSaveRequestDto {
    @NotBlank(message = "El nombre del usuario es obligatorio")
    private String name;

    @PastOrPresent(message = "La Fecha de registro debe ser anterior a la actual")
    private LocalDate fechaRegistro;

    @NotBlank(message = "Rellenar el campo del correo")
    @Email(message = "Correo electrónico no válido")
    private String email;
}
