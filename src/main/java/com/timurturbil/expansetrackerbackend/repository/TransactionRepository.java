package com.timurturbil.expansetrackerbackend.repository;
import com.timurturbil.expansetrackerbackend.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Transaction findById(long id);
    void deleteById(long id);

    Iterable<Transaction> findAllByUserId(Long user_id);
}
