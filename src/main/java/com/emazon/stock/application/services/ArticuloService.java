package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.CrearArticuloUseCase;
import com.emazon.stock.domain.puertos.in.ListArticlesUseCase;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ArticuloService implements CrearArticuloUseCase, ListArticlesUseCase {


    private final CrearArticuloUseCase crearArticuloUseCase;
    private final ListArticlesUseCase listarArticulosUseCase;


    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        return crearArticuloUseCase.guardarArticulo(articulo);
    }


    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return listarArticulosUseCase.listArticles(paginationParams);
    }


}
