package com.emazon.stock.domain;


import com.emazon.stock.application.services.CategoriaService;
import com.emazon.stock.application.usecases.CrearCategoriaUseCaseImpl;
import com.emazon.stock.domain.exception.CategoriaNombreDuplicadoException;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.in.CrearCategoriaUseCase;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@SpringBootTest
public class CategoriaTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;

    @InjectMocks
    private CrearCategoriaUseCaseImpl crearCategoriaUseCaseImpl;

    private CategoriaService categoriaService;

    Categoria categoria1 = new Categoria(0L, "test", "Descripción de categoría 1");

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        categoriaService = new CategoriaService(crearCategoriaUseCaseImpl); // Inicializa CategoriaService aquí

        when(categoriaRepositoryPort.obtenerCategoriaPorNombre(categoria1.getNombre())).thenReturn(Optional.of(categoria1));

    }

    @Test
    void testErrorDuplicateCategoria() {
        // Arrange: Configura el comportamiento esperado



        // Act & Assert: Llama al método a probar y verifica que lanza la excepción
        CategoriaNombreDuplicadoException exception = assertThrows(CategoriaNombreDuplicadoException.class, () -> {
            categoriaService.guardarCategoria(categoria1);
        });

        // Assert: Verifica que el mensaje de la excepción es el esperado
        assertEquals("Este nombre de categoria ya esta registrado", exception.getMessage());

        // Verifica que el método guardarCategoria no se llame al repositorio
       // verify(categoriaRepositoryPort, never()).guardarCategoria(any());
    }

}
