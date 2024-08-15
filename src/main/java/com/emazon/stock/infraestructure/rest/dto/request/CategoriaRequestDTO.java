package com.emazon.stock.infraestructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class CategoriaRequestDTO {

    @Null
    private Long id_categoria;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    @Size(max = 50, message = "El tamaño máximo del nombre es de 50 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción de la categoría es obligatoria.")
    @Size(max = 90, message = "El tamaño máximo de la descripción es de 90 caracteres.")
    private String descripcion;


}
