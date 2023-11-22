package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.DashboardDto;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor

public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping(path = "", produces = "application/json")
    public GenericResponse<DashboardDto> getDashboardData(@RequestHeader("Authorization") String bearerToken){
        return dashboardService.getDashboardData(bearerToken);
    }
}