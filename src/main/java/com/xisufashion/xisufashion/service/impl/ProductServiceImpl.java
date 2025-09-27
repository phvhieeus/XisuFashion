package com.xisufashion.xisufashion.service.impl;

import com.xisufashion.xisufashion.domain.Category;
import com.xisufashion.xisufashion.domain.Product;
import com.xisufashion.xisufashion.dto.request.ProductCreateRequest;
import com.xisufashion.xisufashion.exception.ResourceNotFoundException;
import com.xisufashion.xisufashion.repository.CategoryRepository;
import com.xisufashion.xisufashion.repository.ProductRepository;
import com.xisufashion.xisufashion.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.getCategoryId()));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setColor(request.getColor());
        product.setImages(request.getImages());
        product.setSizes(request.getSizes());
        product.setCategory(category);
        product.setNumRatings(0);

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductCreateRequest request) {
        Product existProduct = getProductById(id);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.getCategoryId()));

        existProduct.setName(request.getName());
        existProduct.setDescription(request.getDescription());
        existProduct.setPrice(request.getPrice());
        existProduct.setQuantity(request.getQuantity());
        existProduct.setColor(request.getColor());
        existProduct.setImages(request.getImages());
        existProduct.setSizes(request.getSizes());
        existProduct.setCategory(category);

        return productRepository.save(existProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
