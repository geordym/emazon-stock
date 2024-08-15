package com.emazon.stock.application.usecases;

import com.emazon.stock.domain.exception.CategoriaNombreDuplicadoException;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.in.CrearCategoriaUseCase;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class CrearCategoriaUseCaseImpl implements CrearCategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepository;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        validarCategoriaNoDuplicada(categoria);
        return categoriaRepository.guardarCategoria(categoria);
    }

    private void validarCategoriaNoDuplicada(Categoria categoria) {
        Optional<Categoria> categoria1 =  categoriaRepository.obtenerCategoriaPorNombre(categoria.getNombre());
        categoria1.ifPresent(existingCategoria -> {
            throw new CategoriaNombreDuplicadoException("Este nombre de categoría '" + categoria.getNombre() + "' ya está registrado.");
        });

    }

}
