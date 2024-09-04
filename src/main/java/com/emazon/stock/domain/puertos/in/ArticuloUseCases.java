package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.Optional;

public interface ArticuloUseCases {

    Articulo saveArticulo(Articulo articulo);
    PaginationCustom listArticles(PaginationParams paginationParams);

    Optional<Articulo> findArticleById(Long articleId);

}
