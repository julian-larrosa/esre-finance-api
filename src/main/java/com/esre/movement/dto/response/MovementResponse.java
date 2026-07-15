package com.esre.movement.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.esre.category.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse {

    private UUID id;
    private BigDecimal amount;
    private String type;
    private LocalDateTime date;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryResponse CategoryResponse;
}
