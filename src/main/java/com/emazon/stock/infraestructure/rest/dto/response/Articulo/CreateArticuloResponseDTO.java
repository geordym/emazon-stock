package com.emazon.stock.infraestructure.rest.dto.response.Articulo;


import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticuloResponseDTO {

    public Long id_articulo;
    public String nombre;
    public String descripcion;
    public Integer cantidad;
    public Double precio;
    public List<CategoryResponseDTO> categorias;
    public Marca marca;


}