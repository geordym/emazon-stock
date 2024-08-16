package com.emazon.stock.domain.puertos.in;


import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Categoria;

import java.util.List;

public interface ListarCategoriasPorArticuloUseCase {

    List<Categoria> listarCategoriasPorArticulo(Articulo articulo);

}
