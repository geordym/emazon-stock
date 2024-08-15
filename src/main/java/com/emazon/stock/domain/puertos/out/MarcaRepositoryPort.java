package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.Marca;

import java.util.Optional;

public interface MarcaRepositoryPort {

    Marca guardarMarca(Marca marca);

    Optional<Marca> obtenerMarcaPorNombre(String nombre);
}
