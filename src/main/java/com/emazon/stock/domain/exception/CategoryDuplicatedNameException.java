package com.emazon.stock.domain.exception;

public class CategoryDuplicatedNameException  extends RuntimeException {

    public CategoryDuplicatedNameException(String message) {
        super(message);
    }
}