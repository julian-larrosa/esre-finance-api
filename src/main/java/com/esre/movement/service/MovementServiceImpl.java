package com.esre.movement.service;

import java.nio.file.DirectoryStream.Filter;
import java.util.List;
import java.util.UUID;

import com.esre.movement.dto.request.CreateMovementRequest;
import com.esre.movement.dto.response.MovementResponse;

public class MovementServiceImpl implements MovementService{

    @Override
    public MovementResponse create(CreateMovementRequest request, UUID userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<MovementResponse> listMovements(UUID userId, Filter filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listMovements'");
    }

}
