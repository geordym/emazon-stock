package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.CrearMarcaUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MarcaService implements CrearMarcaUseCase {

    private final CrearMarcaUseCase crearMarcaUseCase;

    @Override
    public Marca guardarMarca(Marca marca) {
        return crearMarcaUseCase.guardarMarca(marca);
    }
}
