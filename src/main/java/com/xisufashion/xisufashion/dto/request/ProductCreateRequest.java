package com.xisufashion.xisufashion.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {

    private String name;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercent;
    private int quantity;
    private String color;
    private String images;
    private String sizes;
    private Long categoryId;
}
