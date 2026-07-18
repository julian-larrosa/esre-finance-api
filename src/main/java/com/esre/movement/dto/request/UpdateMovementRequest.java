package com.esre.movement.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@RequiredArgsConstructor
public class UpdateMovementRequest {
    
    private UUID categoryId;

    @DecimalMin("0.01")
    private BigDecimal amount;

    @PastOrPresent
    private LocalDateTime date;

    @Size(max = 255)
    private String description;
}
