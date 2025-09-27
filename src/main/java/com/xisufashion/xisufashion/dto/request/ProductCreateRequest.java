package com.xisufashion.xisufashion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "Product description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "50000.0", message = "Price must be at least 50,000 VND")
    @DecimalMax(value = "50000000.0", message = "Price cannot exceed 50,000,000 VND")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be at least 0")
    @Max(value = 10000, message = "Quantity cannot exceed 10000")
    private Integer quantity;

    private String color;

    private String images;

    @NotBlank(message = "Sizes are required")
    private String sizes;

    @NotNull(message = "Category ID is required")
    @Min(value = 1, message = "Category ID must be greater than 0")
    private Long categoryId;
}
