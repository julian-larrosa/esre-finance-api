package com.esre.auth.mapper;

import com.esre.auth.dto.request.RegisterRequest;
import com.esre.auth.dto.response.AuthResponse;
import com.esre.user.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User toEntity(RegisterRequest request);
    
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    AuthResponse toResponse(User user);
}
