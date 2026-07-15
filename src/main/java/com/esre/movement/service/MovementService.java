package com.esre.movement.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.esre.movement.dto.request.CreateMovementRequest;
import com.esre.movement.dto.request.MovementFilterRequest;
import com.esre.movement.dto.response.MovementResponse;

@Service
public interface MovementService {
   

    public MovementResponse create(CreateMovementRequest request, UUID userId);
    public List<MovementResponse> listMovements(UUID userId, MovementFilterRequest filter);
}
