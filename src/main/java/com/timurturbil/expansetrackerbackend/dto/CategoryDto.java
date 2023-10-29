package com.timurturbil.expansetrackerbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    private UserDto user;
    private BigDecimal amount;
}