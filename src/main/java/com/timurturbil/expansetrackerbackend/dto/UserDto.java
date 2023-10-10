package com.timurturbil.expansetrackerbackend.dto;
import lombok.Data;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public UserDto(Long id, String username, String password, String email, String firstName, String lastName){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto(){}

}