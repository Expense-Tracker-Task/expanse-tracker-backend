package com.timurturbil.expansetrackerbackend.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="User") // This tells Hibernate to make a table out of this class
@Table(name="Users")
@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(unique = true, nullable = false) // unique and not null
    private String username;

    @Column(nullable = false) // not null
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public User(String username, String password, String email, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(){}

}