package com.emazon.stock.infraestructure.enums;


public enum ArticleSortBy {
    NAME("name", "nombre"),
    MARK("mark", "marca" ),
    CATEGORY("category", "categories.name");

    private final String value;
    private final String entityAttribute;

    ArticleSortBy(String value, String entityAttribute) {
        this.value = value;
        this.entityAttribute = entityAttribute;
    }

    public String getValue() {
        return value;
    }

    public String toEntity() {
        return entityAttribute;
    }

    public static ArticleSortBy fromValue(String value) {
        for (ArticleSortBy sortBy : ArticleSortBy.values()) {
            if (sortBy.getValue().equalsIgnoreCase(value)) {
                return sortBy;
            }
        }
        throw new IllegalArgumentException("Invalid sortBy value: " + value);
    }
}

