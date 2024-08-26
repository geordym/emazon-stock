package com.emazon.stock.domain.puertos.in;

import com.emazon.stock.domain.model.Marca;

public interface CreateMarcaUseCase {

    Marca saveMarca(Marca marca);
}
