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

    public TransactionDto(Long id, UserDto user, CategoryDto category, BigDecimal amount, LocalDateTime date, String description){
        this.id = id;
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public TransactionDto(){}

}