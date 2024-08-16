package com.emazon.stock.domain;


import com.emazon.stock.application.services.CategoriaService;
import com.emazon.stock.application.usecases.CrearCategoriaUseCaseImpl;
import com.emazon.stock.domain.exception.CategoriaNombreDuplicadoException;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static com.emazon.stock.domain.util.Constantes.LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.LONGITUD_NOMBRE_MAXIMA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CategoriaTest {
    Categoria categoria = new Categoria(0L, "test", "Descripción de categoría 1");
    CategoriaService categoriaService;
    CategoriaRepositoryPort categoriaRepositoryPort;
    CrearCategoriaUseCaseImpl crearCategoriaUseCase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        categoriaRepositoryPort = Mockito.mock(CategoriaRepositoryPort.class);
        crearCategoriaUseCase = new CrearCategoriaUseCaseImpl(categoriaRepositoryPort);
        categoriaService =new CategoriaService(crearCategoriaUseCase);
    }

    @Test
    void testExceptionCuandoSeaDuplicadoElNombre() {
        when(categoriaRepositoryPort.obtenerCategoriaPorNombre(categoria.getNombre())).thenReturn(Optional.of(categoria));
        // Llama al método a probar y verifica que lanza la excepción
        assertThrows(CategoriaNombreDuplicadoException.class, () -> {
            crearCategoriaUseCase.guardarCategoria(categoria);
        });
    }

    @Test
    void testNoThrowExceptionCreandoCategoria() {
        Categoria categoria2 = new Categoria(0L, "unique_categoria" + LocalDate.now().toString(), "testing");
        when(categoriaRepositoryPort.obtenerCategoriaPorNombre(categoria.getNombre())).thenReturn(Optional.of(categoria));

        assertDoesNotThrow(() -> {
            crearCategoriaUseCase.guardarCategoria(categoria2);
        });
    }

    @Test
     void testGuardarCategoriaExitoso() {
        when(categoriaRepositoryPort.guardarCategoria(categoria)).thenReturn(categoria);
        Categoria resultado = categoriaService.guardarCategoria(categoria);
        assertNotNull(resultado);
        assertEquals(categoria, resultado);
        verify(categoriaRepositoryPort).guardarCategoria(categoria);
    }

    @Test
     void testCrearCategoriaConNombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Categoria(1L, "", "Descripción válida");
        });
    }

    @Test
     void testCrearCategoriaConDescripcionNulaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Categoria(1L, "Ropa", null);
        });
    }

    @Test
     void testNombreDemasiadoLargoLanzaExcepcion() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Categoria(1L, "A".repeat(LONGITUD_NOMBRE_MAXIMA + 1), "Descripción válida");
        });
    }

    @Test
     void testDescripcionDemasiadoLargaLanzaExcepcion() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Categoria(1L, "Nombre válido", "A".repeat(LONGITUD_DESCRIPCION_MAXIMA + 1));
        });
    }

    @Test
     void testNombreValidoNoLanzaExcepcion() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            new Categoria(1L, "N".repeat(LONGITUD_NOMBRE_MAXIMA), "D".repeat(LONGITUD_DESCRIPCION_MAXIMA));
        });
    }

    @Test
     void testDescripcionValidaNoLanzaExcepcion() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            new Categoria(1L, "N".repeat(LONGITUD_NOMBRE_MAXIMA), "D".repeat(LONGITUD_DESCRIPCION_MAXIMA));
        });
    }

}
