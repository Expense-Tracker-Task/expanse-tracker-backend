package com.timurturbil.expansetrackerbackend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public CategoryDto(){}
}