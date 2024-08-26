package com.emazon.stock.application.usecases;

import com.emazon.stock.domain.exception.MarcaNombreDuplicadoException;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.in.CrearMarcaUseCase;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CrearMarcaUseCaseImpl implements CrearMarcaUseCase {


    private final MarcaRepositoryPort marcaRepositoryPort;

    @Override
    public Marca guardarMarca(Marca marca) {
        //Validar que el nombre de la marca no este repetido
        validarNombreMarcaUnico(marca.getNombre());

        return marcaRepositoryPort.guardarMarca(marca);
    }

    public void validarNombreMarcaUnico(String nombre) {
        Optional<Marca> marcaExistente = marcaRepositoryPort.obtenerMarcaPorNombre(nombre);
        if (marcaExistente.isPresent()) {
            throw new MarcaNombreDuplicadoException("El nombre de la marca ya existe: " + nombre);
        }
    }
}
