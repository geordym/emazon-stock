package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.implementations.MarcaService;
import com.emazon.stock.domain.model.Marca;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.mapper.MarcaMapper;
import com.emazon.stock.infraestructure.rest.dto.request.Marca.MarcaCreateRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Marca.MarcaCreateResponseDTO;
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
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    @ApiOperation(value = "Retrieve a paginated list of brands",
            notes = """
                    This endpoint allows you to fetch a paginated list of brands from the system. You can customize the response by specifying the page number, page size, sorting field, and sort direction (ascending/descending).
                    
                    **Parameters:**
                    - `page`: The page number to retrieve (starts from 0). Default is 0.
                    - `size`: The number of brands per page. Default is 10.
                    - `sortBy`: The field by which the brands should be sorted (e.g., 'nombre'). Default is 'nombre'.
                    - `ascending`: The sort order (true for ascending, false for descending). Default is true.
                    
                    **Response:**
                    - Returns a paginated list of brands with the specified sorting and pagination.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Brands successfully retrieved"),
            @ApiResponse(code = 400, message = "Bad Request: Invalid pagination or sorting parameters",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationCustom> listMarcas(
            @RequestParam(defaultValue = "0") @ApiParam(value = "The page number to retrieve, starting from 0", defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @ApiParam(value = "The number of brands per page", defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") @ApiParam(value = "The field by which the brands should be sorted (e.g., 'nombre')", defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "true") @ApiParam(value = "The sort order (true for ascending, false for descending)", defaultValue = "true") boolean ascending) {

        // Retrieve paginated list of brands
        PaginationCustom pagination = marcaService.listMarcas(new PaginationParams(page, size, sortBy, ascending));

        // Map domain entities to DTOs
        List<Marca> marcaDomainList = pagination.getContent();
        List<MarcaCreateResponseDTO> marcaDTOList = marcaDomainList.stream()
                .map(MarcaMapper::domainToDto)
                .toList();

        // Update pagination content with DTOs
        pagination.setContent(marcaDTOList);

        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }



    @PostMapping
    @ApiOperation(value = "Create a new brand",
            notes = """
                    This endpoint allows you to create a new brand in the system. The brand must meet the following criteria:
                    
                    **Business Rules:**
                    - The brand name must be unique.
                    
                    **Exceptions:**
                    - `MarcaNombreDuplicadoException`: Thrown if the brand name already exists in the system.
                    
                    **Response:**
                    - Returns a object of type MarcaCreateResponseDTO who represents the entity in the database
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = MarcaCreateResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request: Brand name is duplicated",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<MarcaCreateResponseDTO> createMarca(
            @RequestBody @Valid @ApiParam(value = "Data needed to create a new brand", required = true)
            MarcaCreateRequestDTO marcaCreateRequestDTO) {

        return new ResponseEntity<>(
                MarcaMapper.domainToDto(
                        marcaService.saveMarca(
                                MarcaMapper.dtoToDomain(marcaCreateRequestDTO)
                        )
                ), HttpStatus.OK);
    }


}
