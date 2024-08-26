package com.emazon.stock.infraestructure.rest.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
    public class CategoriaResponseDTO {

    private String nombre;
    private String descripcion;


}
