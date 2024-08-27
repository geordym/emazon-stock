package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.CategoryService;
import com.emazon.stock.domain.exception.CategoryDuplicatedNameException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.mapper.CategoryMapper;
import com.emazon.stock.infraestructure.rest.dto.request.Categoria.CategoryCreateRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Categoria.CategoryResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ApiOperation(value = "Create a new category",
            notes = """
                    This endpoint allows you to create a new category in the system. The category must meet the following criteria:
                    
                    **Business Rules:**
                    - The category name must be unique.
                    
                    **Exceptions:**
                    - `CategoryDuplicatedNameException`: Thrown if the category name already exists in the system.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully created"),
            @ApiResponse(code = 400, message = "Bad Request: Category name is duplicated",
                    response = CategoryDuplicatedNameException.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CategoryResponseDTO> saveCategory(
            @RequestBody @Valid @ApiParam(value = "Data needed to create a new category", required = true)
            CategoryCreateRequestDTO categoria) {

        return new ResponseEntity<>(
                CategoryMapper.domainToDto(
                        categoryService.saveCategory(
                                CategoryMapper.dtoToDomain(categoria)
                        )
                ), HttpStatus.OK);
    }


    @GetMapping
    @ApiOperation(value = "Retrieve a paginated list of categories",
            notes = """
                    This endpoint allows you to fetch a paginated list of categories from the system. You can customize the response by specifying the page number, page size, sorting field, and sort direction (ascending/descending).
                    
                    **Parameters:**
                    - `page`: The page number to retrieve (starts from 0). Default is 0.
                    - `size`: The number of categories per page. Default is 10.
                    - `sortBy`: The field by which the categories should be sorted (e.g., 'name'). Default is 'name'.
                    - `ascending`: The sort order (true for ascending, false for descending). Default is true.
                          
                    **Response:**
                    - Returns a paginated list of categories with the specified sorting and pagination.
                   
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categories successfully retrieved"),
            @ApiResponse(code = 400, message = "Bad Request: Invalid pagination or sorting parameters",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationCustom> listCategories(
            @RequestParam(defaultValue = "0") @ApiParam(value = "The page number to retrieve, starting from 0", defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @ApiParam(value = "The number of categories per page", defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") @ApiParam(value = "The field by which the categories should be sorted (e.g., 'name')", defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") @ApiParam(value = "The sort order (true for ascending, false for descending)", defaultValue = "true") boolean ascending) {

        // Call the service to get the list of categories
        PaginationCustom paginationCustom = categoryService.listCategories(new PaginationParams(page, size, sortBy, ascending));

        return new ResponseEntity<>(paginationCustom, HttpStatus.OK);
    }



}
