package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.CategoryService;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.mapper.CategoryMapper;
import com.emazon.stock.infraestructure.rest.dto.request.Categoria.CategoryCreateRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> saveCategory(@RequestBody @Valid CategoryCreateRequestDTO categoria){
        return new ResponseEntity<>(CategoryMapper.domainToDto(categoryService.saveCategory(CategoryMapper.dtoToDomain(categoria))), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<PaginationCustom> listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {


        // Llamar al servicio para obtener la lista de categorías
        PaginationCustom paginationCustom = categoryService.listCategories(new PaginationParams(page,size,sortBy,ascending));


        return new ResponseEntity<>(paginationCustom, HttpStatus.OK);
    }


}
