package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.SimCardForSearchDto;
import uz.developer.communication_system.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_MANAGER')")
    @GetMapping("/getAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month")
    public ApiResponse getAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(
            @RequestParam Integer branchId,@RequestParam Integer year, @RequestParam Integer month){
        return dashboardService.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(branchId,year,month);
    }

}
