package com.xisufashion.xisufashion.service;

import com.xisufashion.xisufashion.domain.Product;

import java.util.List;

public interface ProductService {

    public Product createProduct(Product product);

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public Product updateProduct(Long id, Product product);

    public void deleteProduct(Long id);
}
