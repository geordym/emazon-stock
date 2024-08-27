package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.CreateArticuloUseCase;
import com.emazon.stock.domain.puertos.in.ListArticlesUseCase;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticuloService implements CreateArticuloUseCase, ListArticlesUseCase {

    private final CreateArticuloUseCase createArticuloUseCase;
    private final ListArticlesUseCase listArticlesUseCase;

    @Override
    public Articulo saveArticulo(Articulo articulo) {
        return createArticuloUseCase.saveArticulo(articulo);
    }


    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return listArticlesUseCase.listArticles(paginationParams);
    }


}
