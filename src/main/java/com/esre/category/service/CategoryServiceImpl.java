package com.esre.category.service;

import com.esre.movement.repository.MovementRepository;
import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.esre.auth.exception.UnauthorizedException;
import com.esre.category.dto.request.CreateCategoryRequest;
import com.esre.category.dto.response.CategoryResponse;
import com.esre.category.entity.Category;
import com.esre.category.exception.CategoryNotFoundException;
import com.esre.category.exception.ResourceAlreadyExistsException;
import com.esre.category.mapper.CategoryMapper;
import com.esre.category.repository.CategoryRepository;
import com.esre.movement.entity.Movement;
import com.esre.user.entity.User;
import com.esre.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final MovementRepository movementRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;

    CategoryServiceImpl(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request, UUID userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new UnauthorizedException("no existe el usuario"));

        if(categoryRepository.existsByNameAndUserId(request.getName(), userId)){
            throw new ResourceAlreadyExistsException("ya existe la categoria");
        }
        Category category = categoryMapper.toEntity(request);
        category.setUser(user);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponse getCategory(UUID categoryId, UUID userId) {
        if(!userRepository.existsById(userId)){
            new UnauthorizedException("no existe el usuario");
        }
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                                              .orElseThrow(() -> new CategoryNotFoundException("no existe la categoria"));
        
        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> listCategories(UUID userId) {
        if(!userRepository.existsById(userId)){
            throw new UnauthorizedException("no existe el usuario");
        }
        List<Category> categories = categoryRepository.findAllByUserId(userId);

        return categories
               .stream()
               .map(categoryMapper::toResponse)
               .toList();
    }

    @Override
    public CategoryResponse updateCategory(UUID categoryId, UUID userId, CreateCategoryRequest request) {
        
    }

    @Override
    public CategoryResponse deleteCategory(UUID categoryId, UUID userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }
}
