package com.xisufashion.xisufashion.service;

import com.xisufashion.xisufashion.domain.Product;
import com.xisufashion.xisufashion.dto.request.ProductCreateRequest;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductCreateRequest req);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, ProductCreateRequest req);

    void deleteProduct(Long id);
}
