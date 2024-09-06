package com.emazon.stock.domain.puertos.out.security;

public interface PasswordEncoderPort {

    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);

}
