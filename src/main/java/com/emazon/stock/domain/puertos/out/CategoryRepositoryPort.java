package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {

    Category saveCategory(Category categoria);

    Optional<Category> getCategoryByName(String nombre);


    PaginationCustom listCategories(PaginationParams paginationParams);


    List<Category> getCategoriesByArticulo(Articulo articulo);

    List<Category> getCategoriesById(List<Long> idList);
}
