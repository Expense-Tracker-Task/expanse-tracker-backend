package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.dto.TransactionDto;
import com.timurturbil.expansetrackerbackend.dto.UserDto;
import com.timurturbil.expansetrackerbackend.entity.Category;
import com.timurturbil.expansetrackerbackend.entity.Transaction;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
import com.timurturbil.expansetrackerbackend.repository.TransactionRepository;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final ModelMapper modelMapper;

    public TransactionService(TransactionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public TransactionDto findTransactionById(int id){
        try {
            Transaction transactionData = repository.findById(id);
            return modelMapper.map(transactionData, TransactionDto.class);
        } catch (Exception e) {
            return null;
        }
    }
    public TransactionDto saveTransaction(TransactionDto transactionDto){
        try {
            Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
            repository.save(transaction);
            return transactionDto;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteTransaction(int id){
        repository.deleteById(id);
    }
    
    public List<TransactionDto> getAllTransactions(){
        try {
            Iterable<Transaction> iterableTransactions = repository.findAll();
            List<TransactionDto> transactionDtoList = new ArrayList<>();
            for(Transaction transaction : iterableTransactions){
                transactionDtoList.add(modelMapper.map(transaction, TransactionDto.class));
            }
            return transactionDtoList;
        } catch (Exception e) {
            return null;
        }
    }

}
