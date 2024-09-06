package com.emazon.stock.infraestructure.rest.dto.response.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorGenericResponseDto {
    private String error;
    private String message;
    private String timestamp;

}