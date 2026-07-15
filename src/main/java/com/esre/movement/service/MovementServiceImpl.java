package com.esre.movement.service;

import java.nio.file.DirectoryStream.Filter;
import java.util.List;
import java.util.UUID;
import java.util.Locale.Category;

import org.springframework.stereotype.Service;

import com.esre.category.repository.CategoryRepository;
import com.esre.movement.dto.request.CreateMovementRequest;
import com.esre.movement.dto.request.MovementFilterRequest;
import com.esre.movement.dto.response.MovementResponse;
import com.esre.movement.mapper.MovementMapper;
import com.esre.movement.repository.MovementRepository;
import com.esre.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService{

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public MovementResponse create(CreateMovementRequest request, UUID userId) {






        return null;
    }

    @Override
    public List<MovementResponse> listMovements(UUID userId, MovementFilterRequest filter) {
        return null;
    }

}
