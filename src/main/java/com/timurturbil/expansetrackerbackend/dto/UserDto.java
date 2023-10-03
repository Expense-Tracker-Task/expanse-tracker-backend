package com.timurturbil.expansetrackerbackend.dto;
import lombok.Data;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String fullName;

}