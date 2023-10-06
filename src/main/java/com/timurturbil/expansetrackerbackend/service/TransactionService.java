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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public TransactionDto findTransactionById(int id){
        Transaction transactionData = transactionRepository.findById(id);

        UserDto user = new UserDto(
                transactionData.getUser().getId(),
                transactionData.getUser().getUsername(),
                transactionData.getUser().getPassword(),
                transactionData.getUser().getEmail(),
                transactionData.getUser().getFirstName() + " " + transactionData.getUser().getLastName()
        );

        CategoryDto category = new CategoryDto(
                transactionData.getCategory().getId(),
                transactionData.getCategory().getName()
        );

        return new TransactionDto(
                transactionData.getId(),
                user,
                category,
                transactionData.getAmount(),
                transactionData.getDate(),
                transactionData.getDescription());
    }
    public TransactionDto saveTransaction(TransactionDto transactionDto){
        User user = userRepository.findById(transactionDto.getUser().getId() == null ? 1 : transactionDto.getUser().getId());
        Category category = categoryRepository.findById(transactionDto.getCategory().getId() == null ? 1 : transactionDto.getCategory().getId());

        Transaction transaction = new Transaction(
                user,
                category,
                transactionDto.getAmount(),
                LocalDateTime.now(),
                transactionDto.getDescription()
        );
        transaction = transactionRepository.save(transaction);
        transactionDto.setId(transaction.getId());
        return transactionDto;
    }

    //TODO: update servisine bakılacak, refactor gerekebilir
    public TransactionDto updateTransaction(TransactionDto transactionDto){
        User user = userRepository.findById(transactionDto.getUser().getId() == null ? 1 : transactionDto.getUser().getId());
        Category category = categoryRepository.findById(transactionDto.getCategory().getId() == null ? 1 : transactionDto.getCategory().getId());
        Transaction transaction = transactionRepository.findById((long) transactionDto.getId());

        transaction.setId(transactionDto.getId());
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction = transactionRepository.save(transaction);
        transactionDto.setId(transaction.getId());
        return transactionDto;
    }

    public void deleteTransaction(int id){
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getAllTransactions(){
        Iterable<Transaction> transactions = transactionRepository.findAll(); //TODO: burada TransactionDto modeline giydirilmeli ama nasıl?
        List<Transaction> transactionList = new ArrayList<>();                //TODO: Her modele giydirme işleminde for içinde user ve category için get isteği atmam lazım
        transactions.forEach(transactionList::add);                           //TODO: ama hiç mantıklı gelmedi. Daha iyi bir çözüm olmalı
        return transactionList;                                               //TODO: hata dönüyor anlaşılan entity dönemiyorum
    }

}



