package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.ListMarcasUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class MarcaService implements ListMarcasUseCase {

    private final ListMarcasUseCase listarMarcasUseCase;

    @Override
    public List<Marca> listMarcas(int page, int size, String sortBy, boolean ascending) {
        return listarMarcasUseCase.listMarcas(page,size,sortBy,ascending);
    }
}
