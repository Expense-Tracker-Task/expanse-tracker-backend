package com.timurturbil.expansetrackerbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    @NotNull(message = "User is mandatory")
    private UserDto user;

    @NotNull(message = "Categories is mandatory")
    private List<CategoryDto> categories;

    @NotNull(message = "Transactions is mandatory")
    private List<TransactionDto> transactions;
}