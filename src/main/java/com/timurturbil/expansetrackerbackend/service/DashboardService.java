package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.dto.*;
import com.timurturbil.expansetrackerbackend.entity.Category;
import com.timurturbil.expansetrackerbackend.entity.Transaction;
import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
import com.timurturbil.expansetrackerbackend.repository.TransactionRepository;
import com.timurturbil.expansetrackerbackend.repository.UserRepository;
import com.timurturbil.expansetrackerbackend.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DashboardService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public GenericResponse<DashboardDto> getDashboardData(String bearerToken){
        try {
            //GET USER ID FROM TOKEN
            int userId = jwtService.extractUserId(bearerToken);

            //GET DATA FROM REPOSITORIES
            User user = userRepository.findById((long) userId);
            Iterable<Category> iterableCategories = categoryRepository.findAllByUserId((long) userId);
            Iterable<Transaction> iterableTransactions = transactionRepository.findAllByUserId((long) userId);

            //MAP ENTITY TO DTO
            UserDto userDto = modelMapper.map(user, UserDto.class);

            List<CategoryDto> categoryDtoList = new ArrayList<>();
            for(Category category : iterableCategories){
                categoryDtoList.add(modelMapper.map(category, CategoryDto.class));
            }

            List<TransactionDto> transactionDtoList = new ArrayList<>();
            for(Transaction transaction : iterableTransactions){
                transactionDtoList.add(modelMapper.map(transaction, TransactionDto.class));
            }

            //SET DASHBOARD DTO DATA
            DashboardDto dashboardDto = new DashboardDto();
            dashboardDto.setUser(userDto);
            dashboardDto.setCategories(categoryDtoList);
            dashboardDto.setTransactions(transactionDtoList);

            return new GenericResponse<>(Constants.SUCCESS, Constants.DASHBOARD_DATA_FOUND, dashboardDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
}