package com.esre.movement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esre.common.dto.ApiResponse;
import com.esre.config.SwaggerConstants;
import com.esre.movement.dto.request.CreateMovementRequest;
import com.esre.movement.dto.request.MovementFilterRequest;
import com.esre.movement.dto.request.UpdateMovementRequest;
import com.esre.movement.dto.response.MovementResponse;
import com.esre.movement.service.MovementService;
import com.esre.user.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/movements")
@Tag(name = "Movimientos")
public class MovementController {
    private final MovementService movementService;

    @PostMapping
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = SwaggerConstants.CREATED, description = "movimiento creado exitosamente")
    @Operation(summary = "Crear nuevo movimiento", description = "Crea un movimiento con una categoria asociada")
    public ResponseEntity<ApiResponse<MovementResponse>> create(@Valid @RequestBody CreateMovementRequest request, 
                                                                @AuthenticationPrincipal User user){
        MovementResponse response = movementService.create(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ApiResponse.created(response));
    }

    @GetMapping
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = SwaggerConstants.OK, description = "movimientos listados exitosamente")
    @Operation(summary = "Listar movimientos", description = "Lista movimientos del usuario")
    public ResponseEntity<ApiResponse<List<MovementResponse>>> listMovements(@AuthenticationPrincipal User user, 
                                                                     @Valid MovementFilterRequest filter){
    
    List<MovementResponse> response = movementService.listMovements(user.getId(), filter);
    return ResponseEntity.status(HttpStatus.OK)
                         .body(ApiResponse.ok(response));
    }

    @GetMapping("/{movementId}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = SwaggerConstants.OK, description = "movimiento mostrado exitosamente")
    @Operation(summary = "Obtener movimiento", description = "Obtiene un movimiento del usuario")
    public ResponseEntity<ApiResponse<MovementResponse>> getMovement(@PathVariable UUID movementId, @AuthenticationPrincipal User user){
        MovementResponse response = movementService.getMovement(movementId, user.getId());
        return ResponseEntity.status(HttpStatus.OK) 
                             .body(ApiResponse.ok(response));
    }

    @DeleteMapping("/{movementId}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = SwaggerConstants.NO_CONTENT, description = "movimiento eliminado exitosamente")
    @Operation(summary = "Eliminar movimiento", description = "Elimina un movimiento del usuario")
    public ResponseEntity<ApiResponse<MovementResponse>> deleteMovement(@PathVariable UUID movementId, @AuthenticationPrincipal User user){
        MovementResponse response = movementService.deleteMovement(movementId, user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(ApiResponse.noContent(response));   
    }

    @PutMapping("/{movementId}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = SwaggerConstants.OK, description = "movimiento actualizado exitosamente")
    @Operation(summary = "Editar movimientos", description = "Edita movimientos del usuario")
    public ResponseEntity<ApiResponse<MovementResponse>> updateMovement(@PathVariable UUID movementId, @AuthenticationPrincipal User user,@Valid @RequestBody UpdateMovementRequest request){
        MovementResponse response = movementService.updateMovement(movementId, user.getId(), request);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(ApiResponse.ok(response));
    }
}
