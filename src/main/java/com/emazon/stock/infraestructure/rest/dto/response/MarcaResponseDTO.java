package com.emazon.stock.infraestructure.rest.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;

    
    
}
