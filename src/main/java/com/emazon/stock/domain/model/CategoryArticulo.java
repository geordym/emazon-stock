package com.emazon.stock.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryArticulo {
    private Long idCategoriaArticulo;
    private Articulo articulo;
    private Category category;


    public CategoryArticulo(Category category) {
        this.category = category;
    }

    public CategoryArticulo(Articulo articulo, Category category) {
        this.articulo = articulo;
        this.category = category;
    }
}
