package com.nttdata.dockerized.postgresql.controller.exception;

public record Error(
        String type,
        String message
) {
}
