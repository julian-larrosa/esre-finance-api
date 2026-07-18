package com.esre.movement.service;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.esre.auth.exception.UnauthorizedException;
import com.esre.category.exception.CategoryNotFoundException;
import com.esre.category.repository.CategoryRepository;
import com.esre.movement.dto.request.CreateMovementRequest;
import com.esre.movement.dto.request.MovementFilterRequest;
import com.esre.movement.dto.request.UpdateMovementRequest;
import com.esre.movement.dto.response.MovementResponse;
import com.esre.movement.entity.Movement;
import com.esre.movement.exception.MovementNotFoundException;
import com.esre.category.entity.Category;
import com.esre.movement.mapper.MovementMapper;
import com.esre.movement.repository.MovementRepository;
import com.esre.user.entity.User;
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
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new UnauthorizedException("no existe el usuario"));
        
        UUID categoryId = request.getCategoryId();
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                                              .orElseThrow(() -> new CategoryNotFoundException("categoria no encontrada"));  
        Movement movement = movementMapper.toEntity(request);
        movement.setCategory(category);
        movement.setUser(user);
        Movement savedMovement = movementRepository.save(movement);
        return movementMapper.toResponse(savedMovement);
    }

    @Override
    public List<MovementResponse> listMovements(UUID userId, MovementFilterRequest filter) {
        userRepository.findById(userId)
                      .orElseThrow(() -> new UnauthorizedException("usuario no encontrado"));

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("date").descending());
        Page<Movement> movements = movementRepository.findAllByUserId(userId, pageable);

        return movements
            .getContent()
            .stream()
            .map(movementMapper::toResponse)
            .toList();
    }

    @Override
    public MovementResponse getMovement(UUID movementId, UUID userId){

        Movement movement = movementRepository.findByIdAndUserId(movementId, userId)
                                              .orElseThrow(() -> new MovementNotFoundException("movimiento no encontrado"));
        return movementMapper.toResponse(movement);
    }

    @Override
    public MovementResponse updateMovement(UUID movementId, UUID userId, UpdateMovementRequest request) {
        Movement movement = movementRepository.findByIdAndUserId(movementId, userId)
                                              .orElseThrow(() -> new MovementNotFoundException("movimiento no encontrado"));
        Category category = categoryRepository.findByIdAndUserId(request.getCategoryId(), userId)
                                              .orElseThrow(() -> new CategoryNotFoundException("categoria no encontrada"));

        movement.setAmount(request.getAmount());
        movement.setCategory(category);
        movement.setDate(request.getDate());
        movement.setDescription(request.getDescription());
        Movement updatedMovement = movementRepository.save(movement);
        return movementMapper.toResponse(updatedMovement);
    }

    @Override
    public MovementResponse deleteMovement(UUID movementId, UUID userId) {
        Movement movement = movementRepository.findByIdAndUserId(movementId, userId)
                                              .orElseThrow(() -> new MovementNotFoundException("movimiento no encontrado"));
        movementRepository.delete(movement);
        return movementMapper.toResponse(movement);
    }

}
