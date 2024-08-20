package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.CrearArticuloUseCase;
import com.emazon.stock.domain.puertos.in.ListarArticulosUseCase;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ArticuloService implements CrearArticuloUseCase, ListarArticulosUseCase {


    private final CrearArticuloUseCase crearArticuloUseCase;
    private final ListarArticulosUseCase listarArticulosUseCase;


    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        return crearArticuloUseCase.guardarArticulo(articulo);
    }


    @Override
    public List<Articulo> listarArticulos(PaginationParams paginationParams) {
        return null;
    }


}
