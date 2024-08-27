package com.emazon.stock.infraestructure.rest.dto.response.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryShortResponseDTO {

    private Long id_category;
    private String name;

}
