package com.emazon.stock.application.implementations;

import com.emazon.stock.application.services.ICategoryService;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.CategoryUseCases;
import com.emazon.stock.domain.puertos.in.CreateCategoryUseCase;
import com.emazon.stock.domain.puertos.in.ListCategoriesUseCase;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryService implements ICategoryService {


    private final CategoryUseCases categoryUseCases;

    @Override
    public Category saveCategory(Category category) {
        return categoryUseCases.saveCategory(category);
    }

    @Override
    public PaginationCustom listCategories(PaginationParams paginationParams) {
        return categoryUseCases.listCategories(paginationParams);
    }

}
