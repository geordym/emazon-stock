package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.ListarMarcasUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class MarcaService implements ListarMarcasUseCase {

    private final ListarMarcasUseCase listarMarcasUseCase;


    @Override
    public List<Marca> listarMarcas(int page, int size, String sortBy, boolean ascending) {
        return null;
    }
}
