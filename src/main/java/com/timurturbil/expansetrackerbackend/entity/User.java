package com.timurturbil.expansetrackerbackend.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name="User") // This tells Hibernate to make a table out of this class
@Table(name="Users")
@Data //includes Getter, Setter, ToString, EqualsAndHashCode
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
}