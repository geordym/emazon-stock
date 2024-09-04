package com.emazon.stock.application.implementations;

import com.emazon.stock.application.services.IArticuloService;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.ArticuloUseCases;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ArticuloService implements IArticuloService {

    private final ArticuloUseCases articuloUseCases;


    @Override
    public Articulo saveArticulo(Articulo articulo) {
        return articuloUseCases.saveArticulo(articulo);
    }


    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return articuloUseCases.listArticles(paginationParams);
    }

    @Override
    public Optional<Articulo> findArticleById(Long articleId) {
        return articuloUseCases.findArticleById(articleId);
    }


}
