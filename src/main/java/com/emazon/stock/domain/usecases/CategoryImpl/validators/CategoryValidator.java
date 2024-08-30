package com.emazon.stock.domain.usecases.CategoryImpl.validators;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.puertos.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryValidator {


    private final CategoryRepositoryPort categoryRepository;

    public void saveCategoryValidate(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.");
        }

        validateName(category.getName());
        validateDescription(category.getDescription());
        validateUniqueName(category.getName());
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }

        if (name.length() > 50) {
            throw new IllegalArgumentException("Category name must not exceed 50 characters.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Category description cannot be null or empty.");
        }

        if (description.length() > 90) {
            throw new IllegalArgumentException("Category description must not exceed 90 characters.");
        }
    }

    private void validateUniqueName(String name) {
        // Retrieve the category by name from the repository
        Optional<Category> existingCategory = categoryRepository.getCategoryByName(name);

        // Check if the Optional is present, meaning the category already exists
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Category name must be unique.");
        }
    }



}
