package com.emazon.stock.domain.puertos.out;


import com.emazon.stock.domain.model.Categoria;

import java.util.Optional;

public interface CategoriaRepositoryPort {

    Categoria guardarCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorNombre(String nombre);
}
