package com.emazon.stock.application.usecases.CategoriaImpl;

import com.emazon.stock.domain.puertos.in.ListCategoriesUseCase;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ListCategoriesUseCaseImpl implements ListCategoriesUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;
    @Override
    public PaginationCustom listCategories(PaginationParams paginationParams) {
        return categoryRepositoryPort.listCategories(paginationParams);
    }


}
