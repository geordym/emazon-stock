package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;

public interface MarcaUseCases {
    Marca saveMarca(Marca marca);
    PaginationCustom listMarcas(PaginationParams paginationParams);

}
