package com.emazon.stock.domain.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaginationParams {
    private int page;
    private int size;
    private String sortBy;
    private boolean ascending;

}
