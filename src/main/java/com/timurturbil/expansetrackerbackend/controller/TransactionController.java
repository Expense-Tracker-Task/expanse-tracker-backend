package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.Constants;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.dto.TransactionDto;
import com.timurturbil.expansetrackerbackend.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new GenericResponse<>(Constants.ERROR, Constants.VALIDATION_FAILED, errors);
    }

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public GenericResponse<TransactionDto> findTransactionById(@PathVariable int id) {
        return transactionService.findTransactionById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<TransactionDto> saveTransaction(@Valid  @RequestBody TransactionDto transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }

//    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
//    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
//        return transactionService.updateTransaction(transactionDto);
//    }

    @DeleteMapping(path = "/{id}")
    public GenericResponse<String> deleteTransaction(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public GenericResponse<List<TransactionDto>> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
}
