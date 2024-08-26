package com.emazon.stock.application.usecases.MarcaImpl;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.ListMarcasUseCase;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ListMarcasUseCaseImpl implements ListMarcasUseCase {

    private final MarcaRepositoryPort marcaRepositoryPort;


    @Override
    public PaginationCustom listMarcas(PaginationParams paginationParams) {
        return marcaRepositoryPort.listMarcas(paginationParams);
    }


}
