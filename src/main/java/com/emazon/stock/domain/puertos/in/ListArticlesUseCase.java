package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

public interface ListArticlesUseCase {

    PaginationCustom listArticles(PaginationParams paginationParams);

}
