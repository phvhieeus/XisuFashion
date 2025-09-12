package com.xisufashion.xisufashion.service.impl;

import com.xisufashion.xisufashion.domain.Product;
import com.xisufashion.xisufashion.repository.ProductRepository;
import com.xisufashion.xisufashion.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existProduct = productRepository.findById(id).orElse(null);
        if(existProduct != null) {
            existProduct.setName(product.getName());
            existProduct.setDescription(product.getDescription());
            existProduct.setMrpPrice(product.getMrpPrice());
            existProduct.setSellingPrice(product.getSellingPrice());
            existProduct.setDiscountPercent(product.getDiscountPercent());
            existProduct.setQuantity(product.getQuantity());
            existProduct.setSizes(product.getSizes());
            return productRepository.save(existProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
