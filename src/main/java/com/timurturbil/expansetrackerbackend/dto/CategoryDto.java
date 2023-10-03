package com.timurturbil.expansetrackerbackend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private List<TransactionDto> transactions = new ArrayList<>();

}