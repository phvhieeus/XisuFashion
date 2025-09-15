package com.xisufashion.xisufashion.service.impl;

import com.xisufashion.xisufashion.domain.Category;
import com.xisufashion.xisufashion.domain.Product;
import com.xisufashion.xisufashion.dto.request.ProductCreateRequest;
import com.xisufashion.xisufashion.repository.CategoryRepository;
import com.xisufashion.xisufashion.repository.ProductRepository;
import com.xisufashion.xisufashion.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductCreateRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            throw new RuntimeException("Category not found with id: " + request.getCategoryId());
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setMrpPrice(request.getMrpPrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setDiscountPercent(request.getDiscountPercent());
        product.setQuantity(request.getQuantity());
        product.setColor(request.getColor());
        product.setImages(request.getImages());
        product.setSizes(request.getSizes());
        product.setCategory(category);
        product.setNumRatings(0);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

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
    public Product updateProduct(Long id, ProductCreateRequest request) {
        Product existProduct = productRepository.findById(id).orElse(null);
        if (existProduct != null) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (category == null) {
                throw new RuntimeException("Category not found");
            }

            existProduct.setName(request.getName());
            existProduct.setDescription(request.getDescription());
            existProduct.setMrpPrice(request.getMrpPrice());
            existProduct.setSellingPrice(request.getSellingPrice());
            existProduct.setDiscountPercent(request.getDiscountPercent());
            existProduct.setQuantity(request.getQuantity());
            existProduct.setColor(request.getColor());
            existProduct.setImages(request.getImages());
            existProduct.setSizes(request.getSizes());
            existProduct.setCategory(category);
            existProduct.setUpdatedAt(LocalDateTime.now());

            return productRepository.save(existProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
