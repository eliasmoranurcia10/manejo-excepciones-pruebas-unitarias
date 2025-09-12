package com.nttdata.microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.nttdata.microservicios.feign")
public class CompositionOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositionOrderApplication.class, args);
    }
}