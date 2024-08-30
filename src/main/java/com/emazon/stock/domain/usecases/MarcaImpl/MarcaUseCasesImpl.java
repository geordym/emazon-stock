package com.emazon.stock.domain.usecases.MarcaImpl;

import com.emazon.stock.domain.exception.MarcaNombreDuplicadoException;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.MarcaUseCases;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.usecases.MarcaImpl.validators.MarcaValidator;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MarcaUseCasesImpl implements MarcaUseCases {
    private final MarcaRepositoryPort marcaRepositoryPort;
    private final MarcaValidator marcaValidator;

    @Override
    public Marca saveMarca(Marca marca) {
        marcaValidator.saveMarcaValidate(marca);
        return marcaRepositoryPort.saveMarca(marca);
    }

    @Override
    public PaginationCustom listMarcas(PaginationParams paginationParams) {
        return marcaRepositoryPort.listMarcas(paginationParams);
    }


}
