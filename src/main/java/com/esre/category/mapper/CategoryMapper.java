package com.esre.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.esre.category.dto.response.CategoryResponse;
import com.esre.category.entity.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);

}
