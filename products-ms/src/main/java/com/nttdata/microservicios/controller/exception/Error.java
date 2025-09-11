package com.nttdata.microservicios.controller.exception;

public record Error(
        String type,
        String message
) {
}
