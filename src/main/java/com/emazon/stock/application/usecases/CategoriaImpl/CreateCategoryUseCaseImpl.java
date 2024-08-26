package com.emazon.stock.application.usecases.CategoriaImpl;

import com.emazon.stock.domain.exception.CategoryDuplicatedNameException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.in.CreateCategoryUseCase;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepository;
    @Override
    public Category saveCategory(Category category) {
        validateCategoryNotDuplicated(category);
        return categoryRepository.saveCategory(category);
    }

    private void validateCategoryNotDuplicated(Category category) {
        Optional<Category> existingCategory = categoryRepository.getCategoryByName(category.getName());
        existingCategory.ifPresent(existing -> {
            throw new CategoryDuplicatedNameException("The category name '" + category.getName() + "' is already registered.");
        });
    }

}
