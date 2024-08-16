package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.CrearArticuloUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ArticuloService implements CrearArticuloUseCase {


    private final CrearArticuloUseCase crearArticuloUseCase;

    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        return crearArticuloUseCase.guardarArticulo(articulo);
    }


}
