package com.emazon.stock.application.usecases;

import com.emazon.stock.domain.puertos.in.ListArticlesUseCase;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListArticlesUseCaseImpl implements ListArticlesUseCase {

    private final ArticuloRepositoryPort articuloRepositoryPort;

    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return articuloRepositoryPort.listArticles(paginationParams);
    }

}
