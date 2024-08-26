package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.CreateMarcaUseCase;
import com.emazon.stock.domain.puertos.in.ListMarcasUseCase;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class MarcaService implements ListMarcasUseCase, CreateMarcaUseCase {

    private final ListMarcasUseCase listarMarcasUseCase;

    private final CreateMarcaUseCase createMarcaUseCase;





    @Override
    public Marca saveMarca(Marca marca) {
        return createMarcaUseCase.saveMarca(marca);
    }


    @Override
    public List<Marca> listMarcas(PaginationParams paginationParams) {
        return listarMarcasUseCase.listMarcas(paginationParams);
    }

}
