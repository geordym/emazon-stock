package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;

public interface ListarArticulosUseCase {


    List<Articulo> listarArticulos(PaginationParams paginationParams);

}
