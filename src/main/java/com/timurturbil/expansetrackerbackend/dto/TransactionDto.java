package com.timurturbil.expansetrackerbackend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class TransactionDto {

    private Long id;
    private UserDto user;
    private CategoryDto category;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;

}