package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;

public interface ArticuloRepositoryPort {

    List<Articulo> listArticles(PaginationParams paginationParams);
    Articulo guardarArticulo(Articulo articulo);
}
