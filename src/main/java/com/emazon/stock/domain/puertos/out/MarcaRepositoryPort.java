package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;
import java.util.Optional;

public interface MarcaRepositoryPort {
    List<Marca> listMarcas(PaginationParams paginationParams);


}
