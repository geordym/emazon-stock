package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

public interface CategoryUseCases {
    Category saveCategory(Category categoria);
    PaginationCustom listCategories(PaginationParams paginationParams);



}
