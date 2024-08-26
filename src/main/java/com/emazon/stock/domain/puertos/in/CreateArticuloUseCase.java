package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Articulo;

public interface CreateArticuloUseCase {

    Articulo saveArticulo(Articulo articulo);

}
