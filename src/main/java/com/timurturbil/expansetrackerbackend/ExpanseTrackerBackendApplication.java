package com.timurturbil.expansetrackerbackend;

//import com.timurturbil.expansetrackerbackend.entity.Category;
//import com.timurturbil.expansetrackerbackend.entity.Transaction;
//import com.timurturbil.expansetrackerbackend.entity.User;
//import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
//import com.timurturbil.expansetrackerbackend.repository.TransactionRepository;
//import com.timurturbil.expansetrackerbackend.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpanseTrackerBackendApplication {
    private static final Logger log = LoggerFactory.getLogger(ExpanseTrackerBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExpanseTrackerBackendApplication.class, args);
    }
}


//    @Bean
//    public CommandLineRunner demo(
//            UserRepository userRepository,
//            TransactionRepository transactionRepository,
//            CategoryRepository categoryRepository) {
//        return (args) -> {
//
//            LocalDateTime now = LocalDateTime.now();
//            User newUser = new User(
//                    "timur",
//                    "timur_sifre",
//                    "timur_email",
//                    "timur_firstname",
//                    "timur_lastname"
//            );
//
//            Category newCategory = new Category("category_name");
//            Transaction newTransaction = new Transaction(
//                    newUser,
//                    newCategory,
//                    new BigDecimal("1000.1"),
//                    now,
//                    "description"
//            );
//            userRepository.save(newUser);
//            categoryRepository.save(newCategory);
//            transactionRepository.save(newTransaction);
//            log.info("************************************************");
//            User myUser = userRepository.findByUsername("timur");
//            log.info("myUser" + myUser.getFirstName());
//            log.info("************************************************");
//
//        };
//    }