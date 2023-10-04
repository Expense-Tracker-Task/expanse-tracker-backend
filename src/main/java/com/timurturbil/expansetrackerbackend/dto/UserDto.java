package com.timurturbil.expansetrackerbackend.dto;
import lombok.Data;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;

    public UserDto(Long id, String username, String password, String email, String fullName){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public UserDto(){}

}