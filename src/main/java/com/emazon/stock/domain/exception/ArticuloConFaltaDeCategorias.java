package com.emazon.stock.domain.exception;

public class ArticuloConFaltaDeCategorias extends RuntimeException {

    public ArticuloConFaltaDeCategorias(String message) {
        super(message);
    }
}