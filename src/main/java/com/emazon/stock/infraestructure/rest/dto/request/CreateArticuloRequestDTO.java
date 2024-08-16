package com.emazon.stock.infraestructure.rest.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticuloRequestDTO {

    public Long id_categorias[];
    public String nombre;
    public String descripcion;
    public Integer cantidad;
    public Double precio;

}
