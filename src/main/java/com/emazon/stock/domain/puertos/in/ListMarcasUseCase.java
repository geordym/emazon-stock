package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;

public interface ListMarcasUseCase {

    PaginationCustom listMarcas(PaginationParams paginationParams);

}
