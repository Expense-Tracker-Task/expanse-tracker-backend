package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.utils.Constants;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.TransactionDto;
import com.timurturbil.expansetrackerbackend.entity.Transaction;
import com.timurturbil.expansetrackerbackend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public GenericResponse<TransactionDto> findTransactionById(int id){
        try {
            Transaction transactionData = repository.findById(id);
            TransactionDto transactionDto = modelMapper.map(transactionData, TransactionDto.class);
            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTION_FOUND, transactionDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<TransactionDto> saveTransaction(TransactionDto transactionDto){
        try {
            Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
            repository.save(transaction);
            transactionDto.setId(transaction.getId());
            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTION_SAVED, transactionDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<String> deleteTransaction(int id){
        try {
            repository.deleteById(id);
            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTION_DELETED, null);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<List<TransactionDto>> getAllTransactions(String bearerToken){
        try {
            int userId = jwtService.extractUserId(bearerToken);
            Iterable<Transaction> iterableTransactions = repository.findAllByUserId((long) userId);
            List<TransactionDto> transactionDtoList = new ArrayList<>();
            for(Transaction transaction : iterableTransactions){
                transactionDtoList.add(modelMapper.map(transaction, TransactionDto.class));
            }
            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTIONS_FOUND, transactionDtoList);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

}
