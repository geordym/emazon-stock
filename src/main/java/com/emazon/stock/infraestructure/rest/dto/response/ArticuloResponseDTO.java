package com.emazon.stock.infraestructure.rest.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloResponseDTO {
    public Long id_articulo;
    public String nombre;
    public String descripcion;
    public Integer cantidad;
    public Double precio;

    public List<CategoriaResponseDTO> categorias;

}
