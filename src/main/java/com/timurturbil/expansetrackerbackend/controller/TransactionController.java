package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.TransactionDto;
import com.timurturbil.expansetrackerbackend.entity.Transaction;
import com.timurturbil.expansetrackerbackend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public TransactionDto findTransactionById(@PathVariable int id) {
        return transactionService.findTransactionById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public TransactionDto saveTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }

//    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
//    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
//        return transactionService.updateTransaction(transactionDto);
//    }

    @DeleteMapping(path = "/{id}")
    public void deleteTransaction(@PathVariable int id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<TransactionDto> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
}
