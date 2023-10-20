package com.timurturbil.expansetrackerbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    @NotBlank(message = "User is mandatory")
    private UserDto user;

    @NotBlank(message = "Category is mandatory")
    private CategoryDto category;

    @NotBlank(message = "Amount is mandatory")
    private BigDecimal amount;

    @NotBlank(message = "Date is mandatory")
    private LocalDateTime date;

    private String description;

}