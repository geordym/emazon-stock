package com.emazon.stock.application.usecases.MarcasImpl;

import com.emazon.stock.application.services.MarcaService;
import com.emazon.stock.application.usecases.MarcaImpl.CreateMarcaUseCaseImpl;
import com.emazon.stock.application.usecases.MarcaImpl.ListMarcasUseCaseImpl;
import com.emazon.stock.domain.exception.MarcaNombreDuplicadoException;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.puertos.out.MarcaRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static com.emazon.stock.domain.util.Constantes.MARCA_LONGITUD_DESCRIPCION_MAXIMA;
import static com.emazon.stock.domain.util.Constantes.MARCA_LONGITUD_NOMBRE_MAXIMA;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateMarcaUseCaseImplTest {

    Marca marca = new Marca(0L, "test", "Descripción de marca 1");
    MarcaService marcaService;
    MarcaRepositoryPort marcaRepositoryPort;
    CreateMarcaUseCaseImpl crearMarcaUseCase;

    ListMarcasUseCaseImpl listMarcaUseCase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        marcaRepositoryPort = Mockito.mock(MarcaRepositoryPort.class);
        crearMarcaUseCase = new CreateMarcaUseCaseImpl(marcaRepositoryPort);
        listMarcaUseCase = new ListMarcasUseCaseImpl(marcaRepositoryPort);
        marcaService  = new MarcaService(listMarcaUseCase,crearMarcaUseCase);
    }


    @Test
    void testExceptionCuandoSeaDuplicadoElNombre() {
        when(marcaRepositoryPort.obtenerMarcaPorNombre(marca.getNombre())).thenReturn(Optional.of(marca));
        // Llama al método a probar y verifica que lanza la excepción
        assertThrows(MarcaNombreDuplicadoException.class, () -> {
            crearMarcaUseCase.saveMarca(marca);
        });
    }

    @Test
    void testNoThrowExceptionCreandoCategoria() {
        Marca marca2 = new Marca(0L, "unique_marca" + LocalDate.now().toString(), "testing");
        when(marcaRepositoryPort.obtenerMarcaPorNombre(marca.getNombre())).thenReturn(Optional.of(marca));

        assertDoesNotThrow(() -> {
            crearMarcaUseCase.saveMarca(marca2);
        });
    }

    @Test
    void testGuardarCategoriaExitoso() {
        when(marcaRepositoryPort.saveMarca(marca)).thenReturn(marca);
        Marca resultado = marcaService.saveMarca(marca);
        assertNotNull(resultado);
        Assertions.assertEquals(resultado, marca);
        verify(marcaRepositoryPort).saveMarca(marca);
    }

    @Test
    void testCrearCategoriaConNombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Marca(1L, "", "Descripción válida");
        });
    }

    @Test
    void testCrearCategoriaConDescripcionNulaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Marca(1L, "Ropa", null);
        });
    }

    @Test
    void testNombreDemasiadoLargoLanzaExcepcion() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Marca(1L, "A".repeat(MARCA_LONGITUD_NOMBRE_MAXIMA + 1), "Descripción válida");
        });
    }

    @Test
    void testDescripcionDemasiadoLargaLanzaExcepcion() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Marca(1L, "Nombre válido", "A".repeat(MARCA_LONGITUD_DESCRIPCION_MAXIMA + 1));
        });
    }

    @Test
    void testNombreValidoNoLanzaExcepcion() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            new Marca(1L, "N".repeat(MARCA_LONGITUD_NOMBRE_MAXIMA), "D".repeat(MARCA_LONGITUD_DESCRIPCION_MAXIMA));
        });
    }

    @Test
    void testDescripcionValidaNoLanzaExcepcion() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            new Marca(1L, "N".repeat(MARCA_LONGITUD_NOMBRE_MAXIMA), "D".repeat(MARCA_LONGITUD_DESCRIPCION_MAXIMA));
        });
    }
}
