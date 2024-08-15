package com.emazon.stock.application.usecases;


import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.in.ListarCategoriasUseCase;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarCategoriasUseCaseImpl implements ListarCategoriasUseCase {


    private final CategoriaRepositoryPort categoriaRepository;

    @Override
    public List<Categoria> listarCategorias(int page, int size, String sortBy, boolean ascending) {
        PaginationParams paginationParams = new PaginationParams(page,size,sortBy,ascending);
        return categoriaRepository.listarCategorias(paginationParams);
    }


}
