package com.xisufashion.xisufashion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 50000, message = "Price must be at least 50,000 VND")
    @Max(value = 50000000, message = "Price cannot exceed 5,000,000 VND")
    private Integer price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be at least 0")
    @Max(value = 10000, message = "Quantity cannot exceed 666")
    private Integer quantity;

    private String color;

    private String images;

    @NotBlank(message = "Sizes are required")
    private String sizes;

    @NotNull(message = "Category ID is required")
    @Min(value = 1, message = "Category ID must be greater than 0")
    private Long categoryId;
}
