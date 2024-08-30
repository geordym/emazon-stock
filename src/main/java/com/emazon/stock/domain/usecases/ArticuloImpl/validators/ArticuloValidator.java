package com.emazon.stock.domain.usecases.ArticuloImpl.validators;

import com.emazon.stock.domain.exception.ArticuloCategoriaRepetidaException;
import com.emazon.stock.domain.exception.ArticuloConExcesoCategoriasException;
import com.emazon.stock.domain.exception.ArticuloConFaltaDeCategoriasException;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticuloValidator {

    private static final int ARTICLE_MAX_CATEGORIES = 3;
    private static final int ARTICLE_MIN_CATEGORIES = 1;

    public void saveArticleValidate(Articulo articulo) {
        checkMaxThreeCategories(articulo);
        checkMinimumOneCategory(articulo);
        checkForDuplicateCategories(articulo);
    }

    private void checkMaxThreeCategories(Articulo articulo) {
        List<Category> categoryList = articulo.getCategories();
        if (categoryList.size() > ARTICLE_MAX_CATEGORIES) {
            throw new ArticuloConExcesoCategoriasException("A maximum of " + ARTICLE_MAX_CATEGORIES + " categories is allowed.");
        }
    }

    private void checkMinimumOneCategory(Articulo articulo) {
        List<Category> categoryList = articulo.getCategories();
        List<Long> categoryIds = categoryList.stream()
                .map(Category::getId_categoria)
                .collect(Collectors.toList());

        if (categoryIds.size() < ARTICLE_MIN_CATEGORIES) {
            throw new ArticuloConFaltaDeCategoriasException("At least " + ARTICLE_MIN_CATEGORIES + " category is required.");
        }
    }

    private void checkForDuplicateCategories(Articulo articulo) {
        List<Category> categoryList = articulo.getCategories();
        List<Long> categoryIds = categoryList.stream()
                .map(Category::getId_categoria)
                .collect(Collectors.toList());

        Set<Long> categoryIdSet = new HashSet<>();
        for (Long id : categoryIds) {
            if (!categoryIdSet.add(id)) {
                throw new ArticuloCategoriaRepetidaException("There are duplicate categories; this is not allowed.");
            }
        }
    }

}
