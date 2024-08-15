package com.emazon.stock.domain.puertos.out;


import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.util.PaginationParams;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {


    Optional<Categoria> obtenerCategoriaPorNombre(String nombre);

    List<Categoria> listarCategorias(PaginationParams paginationParams);

}
