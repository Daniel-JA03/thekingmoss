package com.thekingmoss.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordChecker {
    public static void main(String[] args) {
        String hash = "$2y$10$bYixTpXaX3j6tp8FxReMu.NX7Zp10bkYIDR7E2AtbeQ387NOWZ6LO"; // Reemplaza con el hash de tu BD
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Prueba con posibles contraseñas (usa las que hayas usado al registrar)
        System.out.println("¿Coincide con 'TuClaveSuperSecretaMuyLarga1234567890'? "
                + encoder.matches("TuClaveSuperSecretaMuyLarga1234567890", hash));
    }
}


