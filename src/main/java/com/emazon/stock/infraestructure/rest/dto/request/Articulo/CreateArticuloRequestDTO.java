package com.emazon.stock.infraestructure.rest.dto.request.Articulo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticuloRequestDTO {

    @NotEmpty(message = "Category IDs cannot be empty.")
    private Long[] id_categories;

    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Name must be at most 100 characters long.")
    private String name;

    @NotBlank(message = "Description is required.")
    @Size(max = 255, message = "Description must be at most 255 characters long.")
    private String description;

    @Min(value = 0, message = "Quantity must be a non-negative integer.")
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be a positive number.")
    private Double price;

    @Positive(message = "Marca ID must be a positive number.")
    private long id_marca;

}
