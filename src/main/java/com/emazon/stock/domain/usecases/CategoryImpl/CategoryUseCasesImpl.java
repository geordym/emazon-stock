package com.emazon.stock.domain.usecases.CategoryImpl;

import com.emazon.stock.domain.exception.CategoryDuplicatedNameException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.CategoryUseCases;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryUseCasesImpl implements CategoryUseCases {

    private final CategoryRepositoryPort categoryRepositoryPort;
    @Override
    public Category saveCategory(Category category) {
        validateCategoryNotDuplicated(category);
        return categoryRepositoryPort.saveCategory(category);
    }

    @Override
    public PaginationCustom listCategories(PaginationParams paginationParams) {
        return categoryRepositoryPort.listCategories(paginationParams);
    }


    private void validateCategoryNotDuplicated(Category category) {
        Optional<Category> existingCategory = categoryRepositoryPort.getCategoryByName(category.getName());
        existingCategory.ifPresent(existing -> {
            throw new CategoryDuplicatedNameException("The category name '" + category.getName() + "' is already registered.");
        });
    }

}
