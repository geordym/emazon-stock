package com.emazon.stock.domain.puertos.out;


import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {

    Categoria guardarCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorNombre(String nombre);

    List<Categoria> obtenerCategoriasPorArticulo(Articulo articulo);

    List<Categoria> obtenerCategoriasPorId(List<Long> idList);



}
