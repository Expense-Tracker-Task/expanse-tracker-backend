package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.TransactionDto;
import com.timurturbil.expansetrackerbackend.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GenericResponse<TransactionDto> findTransactionById(@PathVariable int id) {
        return transactionService.findTransactionById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<TransactionDto> saveTransaction(@Valid  @RequestBody TransactionDto transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }

    @DeleteMapping(path = "/{id}")
    public GenericResponse<String> deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

    @GetMapping(path = "", produces = "application/json")
    public GenericResponse<List<TransactionDto>> getAllTransactions(@RequestHeader("Authorization") String bearerToken){
        return transactionService.getAllTransactions(bearerToken);
    }
}
