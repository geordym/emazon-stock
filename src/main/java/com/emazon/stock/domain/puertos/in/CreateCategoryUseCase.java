package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Category;

public interface CreateCategoryUseCase {
    Category saveCategory(Category categoria);

}
