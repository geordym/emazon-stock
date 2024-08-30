package com.emazon.stock.domain.usecases.MarcaImpl;

import com.emazon.stock.domain.exception.MarcaNombreDuplicadoException;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.MarcaUseCases;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MarcaUseCasesImpl implements MarcaUseCases {
    private final MarcaRepositoryPort marcaRepositoryPort;


    @Override
    public Marca saveMarca(Marca marca) {
        validarNombreMarcaUnico(marca.getNombre());

        return marcaRepositoryPort.saveMarca(marca);
    }

    public void validarNombreMarcaUnico(String nombre) {
        Optional<Marca> marcaExistente = marcaRepositoryPort.obtenerMarcaPorNombre(nombre);
        if (marcaExistente.isPresent()) {
            throw new MarcaNombreDuplicadoException("El nombre de la marca ya existe: " + nombre);
        }
    }

    @Override
    public PaginationCustom listMarcas(PaginationParams paginationParams) {
        return marcaRepositoryPort.listMarcas(paginationParams);
    }


}
