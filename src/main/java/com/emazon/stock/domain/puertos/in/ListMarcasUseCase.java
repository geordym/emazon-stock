package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Marca;

import java.util.List;

public interface ListMarcasUseCase {

    List<Marca> listMarcas(int page, int size, String sortBy, boolean ascending);

}
