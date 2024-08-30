package com.emazon.stock.domain.usecases.MarcaImpl.validators;

import com.emazon.stock.domain.exception.MarcaNombreDuplicadoException;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class MarcaValidator {

    private final MarcaRepositoryPort marcaRepositoryPort;
    public void saveMarcaValidate(Marca marca){
        validateUniqueBrandName(marca.getNombre());
    }

    protected void validateUniqueBrandName(String name) {
        Optional<Marca> existingBrand = marcaRepositoryPort.obtenerMarcaPorNombre(name);
        if (existingBrand.isPresent()) {
            throw new MarcaNombreDuplicadoException("The brand name already exists: " + name);
        }
    }

}
