package com.emazon.stock.domain.exception;

public class ArticuloNoEncontradoException extends RuntimeException {

    public ArticuloNoEncontradoException(String message) {
        super(message);
    }
}