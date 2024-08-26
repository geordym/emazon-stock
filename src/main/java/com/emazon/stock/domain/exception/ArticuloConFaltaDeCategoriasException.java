package com.emazon.stock.domain.exception;

public class ArticuloConFaltaDeCategoriasException extends RuntimeException {

    public ArticuloConFaltaDeCategoriasException(String message) {
        super(message);
    }
}