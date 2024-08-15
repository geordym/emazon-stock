package com.emazon.stock.domain;


import com.emazon.stock.application.services.CategoriaService;
import com.emazon.stock.domain.model.Categoria;
import com.emazon.stock.domain.puertos.out.CategoriaRepositoryPort;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class CategoriaTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;

    private CategoriaService categoriaService;

    Categoria categoria1 = new Categoria(0L, "test", "Descripción de categoría 1");



}
