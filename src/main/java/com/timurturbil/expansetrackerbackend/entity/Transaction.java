package com.timurturbil.expansetrackerbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="Transaction") // This tells Hibernate to make a table out of this class
@Table(name="Transactions")
@Data //includes Getter, Setter, ToString, EqualsAndHashCode

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date;

    private String description;

    // One user van be associated with many transactions
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One category can be associated with many transactions
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Transaction(String name, BigDecimal amount, LocalDateTime date, String description, User user, Category category){
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.user = user;
        this.category = category;
    }

    public Transaction(){}

}