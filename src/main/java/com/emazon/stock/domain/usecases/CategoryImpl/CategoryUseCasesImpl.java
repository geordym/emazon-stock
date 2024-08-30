package com.emazon.stock.domain.usecases.CategoryImpl;

import com.emazon.stock.domain.exception.CategoryDuplicatedNameException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.CategoryUseCases;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.usecases.CategoryImpl.validators.CategoryValidator;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryUseCasesImpl implements CategoryUseCases {

    private final CategoryRepositoryPort categoryRepositoryPort;
    private final CategoryValidator categoryValidator;

    @Override
    public Category saveCategory(Category category) {
        categoryValidator.saveCategoryValidate(category);
        return categoryRepositoryPort.saveCategory(category);
    }

    @Override
    public PaginationCustom listCategories(PaginationParams paginationParams) {
        return categoryRepositoryPort.listCategories(paginationParams);
    }



}
