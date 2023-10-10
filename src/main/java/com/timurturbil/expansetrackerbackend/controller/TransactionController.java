package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.Response;
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
    public Response<TransactionDto> findTransactionById(@PathVariable int id) {
        return transactionService.findTransactionById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public Response<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }

//    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
//    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
//        return transactionService.updateTransaction(transactionDto);
//    }

    @DeleteMapping(path = "/{id}")
    public Response<String> deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Response<List<TransactionDto>> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
}
