package com.esre.category.service;

import java.util.List;
import java.util.UUID;

import com.esre.category.dto.request.CreateCategoryRequest;
import com.esre.category.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest request, UUID userId);
    CategoryResponse getCategory(UUID categoryId, UUID userId);
    List<CategoryResponse> listCategories(UUID userId);
    CategoryResponse updateCategory(UUID categoryId, UUID userId, CreateCategoryRequest request);
    CategoryResponse deleteCategory(UUID categoryId, UUID userId);
}
