package com.xisufashion.xisufashion.service;

import com.xisufashion.xisufashion.domain.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(Category category);

    public Category getCategoryById(Long id);

    public List<Category> getAllCategories();

    public Category updateCategory(Long id, Category category);

    public void deleteCategory(Long id);
}
