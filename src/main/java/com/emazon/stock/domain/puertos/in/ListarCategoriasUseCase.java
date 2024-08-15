package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Categoria;

import java.util.List;

public interface ListarCategoriasUseCase {
    List<Categoria> listarCategorias(int page, int size, String sortBy, boolean ascending);

}
