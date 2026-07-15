package com.esre.movement.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementFilterRequest {
    
    
    private UUID categoryId;

     @Pattern(regexp = "INCOME|EXPENSE")
    private String type;

    @Size(max = 255)
    private String search;

    @Positive
    private Integer page = 0;

    @Positive
    @Max(value = 100)
    private Integer size = 10;

    private String sort = "date,desc";

    @PastOrPresent
    private LocalDate startDate;

    @PastOrPresent
    private LocalDate endDate;

}
