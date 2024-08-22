package com.emazon.stock.application.usecases;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.puertos.in.ListarArticulosUseCase;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarArticulosUseCaseImpl implements ListarArticulosUseCase {

    private final ArticuloRepositoryPort articuloRepositoryPort;

    @Override
    public List<Articulo> listarArticulos(PaginationParams paginationParams) {
        return articuloRepositoryPort.listArticles(paginationParams);
    }


}
