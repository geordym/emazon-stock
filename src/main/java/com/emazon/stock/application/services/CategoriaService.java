package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.in.ListarCategoriasUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class CategoriaService implements ListarCategoriasUseCase {


    private ListarCategoriasUseCase listarCategoriasUseCase;

    public CategoriaService(ListarCategoriasUseCase listarCategoriasUseCase) {
        this.listarCategoriasUseCase = listarCategoriasUseCase;
    }


    @Override
    public List<Categoria> listarCategorias(int page, int size, String sortBy, boolean ascending) {
        return listarCategoriasUseCase.listarCategorias(page,size,sortBy,ascending);
    }


}
