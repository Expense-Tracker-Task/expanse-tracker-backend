package com.timurturbil.expansetrackerbackend.repository;
import com.timurturbil.expansetrackerbackend.entity.Transaction;
import org.springframework.data.repository.CrudRepository;


public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	Transaction findById(long id);
}
