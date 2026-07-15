package com.esre.category.repository;

import java.util.UUID;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByIdAndUserId(UUID id, UUID userId);

    List<Category> findAllByUserId(UUID userId);

    boolean existsByNameAndUserId(String name, UUID userId);
}
