package com.emazon.stock.application.services;

import com.emazon.stock.application.usecases.CrearCategoriaUseCaseImpl;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.in.CrearCategoriaUseCase;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class CategoriaService implements CrearCategoriaUseCase {


    private CrearCategoriaUseCase createCategoryUseCase;

    public CategoriaService(CrearCategoriaUseCaseImpl crearCategoriaUseCase) {
        this.createCategoryUseCase = crearCategoriaUseCase;
    }


    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return createCategoryUseCase.guardarCategoria(categoria);
    }


}
