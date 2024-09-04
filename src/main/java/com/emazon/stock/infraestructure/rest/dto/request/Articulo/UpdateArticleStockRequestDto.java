package com.emazon.stock.infraestructure.rest.dto.request.Articulo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateArticleStockRequestDto {
    private Long supplyId;
    private Long articleId;
    private Integer quantity;
}
