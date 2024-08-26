package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.services.ArticuloService;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.mapper.CategoriaMapper;
import com.emazon.stock.infraestructure.repositories.ArticuloCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.repositories.CategoriaCrudRepositoryMySQL;
import com.emazon.stock.infraestructure.rest.dto.response.ArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.CategoriaResponseDTO;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Api(tags = "Articles", description = "Operations related to articles management")
public class ArticuloController {

    private final ArticuloService articuloService;
    private final CategoriaCrudRepositoryMySQL categoriaCrudRepositoryMySQL;
    private final ArticuloCrudRepositoryMySQL articuloCrudRepositoryMySQL;


    @ApiOperation(value = "Get a list of articles",    notes = """
            This endpoint allows you to fetch a paginated list of articles available in the system.
            You can customize the response by specifying the page number, page size, sorting field, 
            and sort direction (ascending/descending).
            
            **Parameters:**
            - `page`: The page number to retrieve (starts from 0).
            - `size`: The number of articles per page.
            - `sortBy`: The field by which the articles should be sorted (e.g., 'nombre').
            - `ascending`: The sort order (true for ascending, false for descending).
            """
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<PaginationCustom> listArticles(
            @ApiParam(value = "Page number (starts from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "Number of articles per page", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @ApiParam(
                    value = "Field by which the articles should be sorted",
                    allowableValues = "id_articulo, nombre, descripcion, cantidad, precio",
                    example = "nombre"
            )            @RequestParam(defaultValue = "nombre") String sortBy,
            @ApiParam(value = "Sort direction (true for ascending, false for descending)",
                    allowableValues="true,false"
                    , example = "true")
            @RequestParam(defaultValue = "true") boolean ascending)  {

        // Llamar al servicio para obtener la lista de categorías
        PaginationCustom pagination = articuloService.
                listArticles(new PaginationParams(page, size, sortBy, ascending));

        List<Articulo> articleDomainList = pagination.getContent();
        List<ArticuloResponseDTO> articleDTOList = articleDomainList.stream()
                .map(ArticuloMapper::domainToDto)
                .toList();

        pagination.setContent(articleDTOList);

        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

}
