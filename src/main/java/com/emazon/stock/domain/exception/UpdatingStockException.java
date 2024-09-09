package com.emazon.stock.domain.exception;


public class UpdatingStockException extends Exception {
    public UpdatingStockException(Exception e) {
        super(ExceptionConstants.UPDATING_STOCK_EXCEPTION_MESSAGE, e);
    }
}