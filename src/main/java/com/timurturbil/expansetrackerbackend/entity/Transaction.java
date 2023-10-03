package com.timurturbil.expansetrackerbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="Transaction") // This tells Hibernate to make a table out of this class
@Table(name="Transactions")
@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One user van be associated with many transactions
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One category can be associated with many transactions
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date;

    private String description;

    public Transaction(User user, Category category, BigDecimal amount, LocalDateTime date, String description){
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Transaction(){}

}