package com.timurturbil.expansetrackerbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Entity(name="Category") // This tells Hibernate to make a table out of this class
@Table(name="Categories")
@Data //includes Getter, Setter, ToString, EqualsAndHashCode
public class Category {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(nullable = false) // not null
    private String name;

    // One user can be associated with many categories
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;
}