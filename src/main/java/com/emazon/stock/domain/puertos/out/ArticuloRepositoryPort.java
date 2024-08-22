package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;

public interface ArticuloRepositoryPort {

    PaginationCustom listArticles(PaginationParams paginationParams);


    Articulo guardarArticulo(Articulo articulo);
}
