package com.emazon.stock.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaArticulo {
    private Long idCategoriaArticulo;
    private Articulo articulo;
    private Categoria categoria;


    public CategoriaArticulo(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaArticulo(Articulo articulo, Categoria categoria) {
        this.articulo = articulo;
        this.categoria = categoria;
    }
}

