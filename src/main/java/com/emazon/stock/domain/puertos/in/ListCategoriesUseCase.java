package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

public interface ListCategoriesUseCase {

    PaginationCustom listCategories(PaginationParams paginationParams);

}
