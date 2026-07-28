package com.esre.movement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.esre.movement.dto.request.CreateMovementRequest;
import com.esre.movement.dto.response.MovementResponse;
import com.esre.movement.entity.Movement;

@Mapper(
    componentModel = "spring", 
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MovementMapper {

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Movement toEntity(CreateMovementRequest request);

    MovementResponse toResponse(Movement movement);
}
