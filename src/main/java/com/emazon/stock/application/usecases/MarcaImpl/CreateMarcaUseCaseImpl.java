package com.emazon.stock.application.usecases.MarcaImpl;

import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.CreateMarcaUseCase;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateMarcaUseCaseImpl implements CreateMarcaUseCase {

    private final MarcaRepositoryPort marcaRepositoryPort;


    @Override
    public Marca saveMarca(Marca marca) {
        return marcaRepositoryPort.saveMarca(marca);
    }



}
