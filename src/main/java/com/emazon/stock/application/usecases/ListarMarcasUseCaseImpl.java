package com.emazon.stock.application.usecases;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.ListarMarcasUseCase;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ListarMarcasUseCaseImpl implements ListarMarcasUseCase {


    private final MarcaRepositoryPort marcaRepositoryPort;

    @Override
    public List<Marca> listarMarcas(int page, int size, String sortBy, boolean ascending) {
        PaginationParams paginationParams = new PaginationParams(page,size,sortBy,ascending);
        return marcaRepositoryPort.listarMarcas(paginationParams);
    }



}
