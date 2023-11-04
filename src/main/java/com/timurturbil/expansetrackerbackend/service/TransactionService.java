package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.entity.Category;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
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
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public GenericResponse<TransactionDto> findTransactionById(int id){
        try {
            Transaction transactionData = transactionRepository.findById(id);
            TransactionDto transactionDto = modelMapper.map(transactionData, TransactionDto.class);
            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTION_FOUND, transactionDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<TransactionDto> saveTransaction(TransactionDto transactionDto){
        try {
            //MAP DTO TO ENTITY
            Transaction transaction = modelMapper.map(transactionDto, Transaction.class);

            //SAVE TRANSACTION
            transactionRepository.save(transaction);

            //UPDATE CATEGORY
            Category category = categoryRepository.findById(transactionDto.getCategory().getId()).orElse(new Category());
            if(transaction.isExpense()){
                category.setAmount(category.getAmount().subtract(transaction.getAmount()));
            }else{
                category.setAmount(category.getAmount().add(transaction.getAmount()));
            }
            categoryRepository.save(category);

            //UPDATE USER
            User user = userRepository.findById(transactionDto.getUser().getId()).orElse(new User());
            if(transaction.isExpense()) {
                user.setBalance(user.getBalance().subtract(transaction.getAmount()));
            }else{
                user.setBalance(user.getBalance().add(transaction.getAmount()));
            }
            userRepository.save(user);

            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTION_SAVED, transactionDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<String> deleteTransaction(int id){
        try {
            //GET TRANSACTION
            Transaction transaction = transactionRepository.findById(id);

            //DELETE TRANSACTION
            transactionRepository.deleteById(id);

            //UPDATE CATEGORY
            Category category = categoryRepository.findById(transaction.getCategory().getId()).orElse(new Category());
            if(transaction.isExpense()){
                category.setAmount(category.getAmount().add(transaction.getAmount()));
            }else{
                category.setAmount(category.getAmount().subtract(transaction.getAmount()));
            }
            categoryRepository.save(category);

            //UPDATE USER
            User user = userRepository.findById(transaction.getUser().getId()).orElse(new User());
            if(transaction.isExpense()) {
                user.setBalance(user.getBalance().add(transaction.getAmount()));
            }else{
                user.setBalance(user.getBalance().subtract(transaction.getAmount()));
            }
            userRepository.save(user);

            return new GenericResponse<>(Constants.SUCCESS, Constants.TRANSACTION_DELETED, null);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    public GenericResponse<List<TransactionDto>> getAllTransactions(String bearerToken){
        try {
            int userId = jwtService.extractUserId(bearerToken);
            Iterable<Transaction> iterableTransactions = transactionRepository.findAllByUserId((long) userId);
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
