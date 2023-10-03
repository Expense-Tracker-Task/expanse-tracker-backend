package com.timurturbil.expansetrackerbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity(name="Category") // This tells Hibernate to make a table out of this class
@Table(name="Categories")
@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor

public class Category {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(nullable = false) // not null
    private String name;

    // One category can be associated with many transactions
    @OneToMany(mappedBy = "category") // mappedBy = "category" means that the category field in the Transaction class is the owning side of the relationship
    private List<Transaction> transactions = new ArrayList<>();

    public Category(String name){
        this.name = name;
    }

    public Category(){}

}