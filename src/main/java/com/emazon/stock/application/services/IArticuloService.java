package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.ArticuloUseCases;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.rest.dto.request.Articulo.UpdateArticleStockRequestDto;
import com.emazon.stock.infraestructure.rest.dto.response.GenericResponseDto;

import java.util.Optional;

public interface IArticuloService {
    Articulo saveArticulo(Articulo articulo);
    PaginationCustom listArticles(PaginationParams paginationParams);
    Optional<Articulo> findArticleById(Long articleId);
    void updateArticleStock(UpdateArticleStockRequestDto updateArticleStockRequestDto);
}
