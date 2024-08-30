package com.emazon.stock.application.implementations;

import com.emazon.stock.application.services.IMarcaService;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.MarcaUseCases;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MarcaService implements IMarcaService {

    private final MarcaUseCases marcaUseCases;


    @Override
    public Marca saveMarca(Marca marca) {
        return marcaUseCases.saveMarca(marca);
    }

    @Override
    public PaginationCustom listMarcas(PaginationParams paginationParams) {
        return marcaUseCases.listMarcas(paginationParams);
    }

}
