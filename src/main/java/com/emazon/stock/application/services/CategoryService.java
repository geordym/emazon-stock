package com.emazon.stock.application.services;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.CreateCategoryUseCase;
import com.emazon.stock.domain.puertos.in.ListCategoriesUseCase;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryService implements CreateCategoryUseCase, ListCategoriesUseCase {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    @Override
    public Category saveCategory(Category category) {
        return createCategoryUseCase.saveCategory(category);
    }

    @Override
    public PaginationCustom listCategories(PaginationParams paginationParams) {
        return listCategoriesUseCase.listCategories(paginationParams);
    }
}
