package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.Articulo;

public interface ArticuloRepositoryPort {

    Articulo saveArticulo(Articulo articulo);

}
