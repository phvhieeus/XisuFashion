package com.xisufashion.xisufashion.service;

import com.xisufashion.xisufashion.domain.Product;
import com.xisufashion.xisufashion.dto.request.ProductCreateRequest;

import java.util.List;

public interface ProductService {

    public Product createProduct(ProductCreateRequest req);

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public Product updateProduct(Long id, ProductCreateRequest req);

    public void deleteProduct(Long id);
}
