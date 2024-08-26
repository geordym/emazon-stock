package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.CreateArticuloUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticuloService implements CreateArticuloUseCase {

    private final CreateArticuloUseCase createArticuloUseCase;

    @Override
    public Articulo saveArticulo(Articulo articulo) {
        return createArticuloUseCase.saveArticulo(articulo);
    }


}
