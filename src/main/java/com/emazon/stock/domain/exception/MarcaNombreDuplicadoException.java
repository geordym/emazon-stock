package com.emazon.stock.domain.exception;

public class MarcaNombreDuplicadoException extends RuntimeException {

    public MarcaNombreDuplicadoException(String message) {
        super(message);
    }
}