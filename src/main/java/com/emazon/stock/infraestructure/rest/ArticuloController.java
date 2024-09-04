package com.emazon.stock.infraestructure.rest;


import com.emazon.stock.application.implementations.ArticuloService;
import com.emazon.stock.domain.exception.ArticuloNoEncontradoException;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.emazon.stock.infraestructure.enums.ArticleSortBy;
import com.emazon.stock.infraestructure.mapper.ArticuloMapper;
import com.emazon.stock.infraestructure.rest.dto.request.Articulo.CreateArticuloRequestDTO;
import com.emazon.stock.infraestructure.rest.dto.request.Articulo.UpdateArticleStockRequestDto;
import com.emazon.stock.infraestructure.rest.dto.response.Articulo.ArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.Articulo.CreateArticuloResponseDTO;
import com.emazon.stock.infraestructure.rest.dto.response.GenericResponseDto;
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
import java.util.Optional;

import static com.emazon.stock.infraestructure.rest.constants.ResponseConstants.ARTICLE_UPDATE_STOCK_MESSAGE;


@RestController
@RequestMapping("/api/articulos")
@RequiredArgsConstructor
public class ArticuloController {


    private final ArticuloService articuloService;

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticuloResponseDTO> getArticleById(@PathVariable("articleId") Long articleId){

        Optional<Articulo> articuloOptional = articuloService.findArticleById(articleId);
        if(articuloOptional.isEmpty()){
            throw new ArticuloNoEncontradoException("Article with id:" + articleId + "Has been not found");
        }

        return new ResponseEntity<>(ArticuloMapper.domainToDto(articuloOptional.get()), HttpStatus.OK);
    }


    @ApiOperation(value = "Get a list of articles",    notes = """
            This endpoint allows you to fetch a paginated list of articles available in the system.
            You can customize the response by specifying the page number, page size, sorting field, 
            and sort direction (ascending/descending).
            
            **Parameters:**
            - `page`: The page number to retrieve (starts from 0).
            - `size`: The number of articles per page.
            - `sortBy`: The field by which the articles should be sorted (e.g., 'nombre').
            - `ascending`: The sort order (true for ascending, false for descending).
            
            
            **Response:**
            - Returns a paginated list of articulos with the specified sorting and pagination.
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
                    allowableValues = "name,mark,category",
                    example = "name"
            )
            @RequestParam(defaultValue = "name") String sortBy,
            @ApiParam(value = "Sort direction (true for ascending, false for descending)",
                    allowableValues="true,false"
                    , example = "true")
            @RequestParam(defaultValue = "true") boolean ascending)  {

        try {
            ArticleSortBy sortByEnum = ArticleSortBy.fromValue(sortBy);
            // Proceed with valid sortBy value
        } catch (IllegalArgumentException e) {
            // Handle invalid sortBy value (e.g., return a 400 Bad Request response)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


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

    @PostMapping
    @ApiOperation(value = "Create a new article",
            notes = """
                    This endpoint allows you to create a new article in the system. The article must meet the following criteria:
                    
                    **Business Rules:**
                    - The article must have at least one category.
                    - The article cannot have more than 3 categories.
                    - The article cannot have duplicate categories.

                    **Exceptions:**
                    - `ArticuloConExcesoCategoriasException`: Thrown if the article has more than 3 categories.
                    - `ArticuloConFaltaDeCategoriasException`: Thrown if the article has no categories.
                    - `ArticuloCategoriaRepetidaException`: Thrown if the article has duplicate categories.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article successfully created"),
            @ApiResponse(code = 400, message = "Bad Request: Exceeds category limits, lacks categories, or has duplicate categories",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CreateArticuloResponseDTO>
    saveArticulo(@RequestBody @ApiParam(value = "Data needed to create a new article", required = true)
                @Valid CreateArticuloRequestDTO createArticuloRequestDTO) {

        return new ResponseEntity<>(
                ArticuloMapper.domainToRequestCreateResponse(
                        articuloService.saveArticulo(
                                ArticuloMapper.requestCreateArticuloToDomain(createArticuloRequestDTO))),
                HttpStatus.OK);
    }

    @PutMapping("/stock")
    public ResponseEntity<GenericResponseDto> updateArticleStock(@RequestBody UpdateArticleStockRequestDto updateArticleStockRequestDto){
        articuloService.updateArticleStock(updateArticleStockRequestDto);
        GenericResponseDto genericResponseDto = new GenericResponseDto(ARTICLE_UPDATE_STOCK_MESSAGE);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }

}
