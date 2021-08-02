package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.entity.CodesCompany;
import uz.developer.communication_system.entity.Branch;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.CodesCompanyRepository;
import uz.developer.communication_system.repository.BranchRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/codesCompany")
public class CodesCompanyController {
    @Autowired
    CodesCompanyRepository codesCompanyRepository;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody CodesCompany codesCompany){
        boolean b = codesCompanyRepository.existsByCompanyCode(codesCompany.getCompanyCode());
        if (b) return new ApiResponse("Sorry.Code will be saved ",false);
        codesCompanyRepository.save(codesCompany);
         return new ApiResponse("successfully saved ",false);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable int id, @RequestBody CodesCompany codesCompany){
        Optional<CodesCompany> optionalCodesCompany = codesCompanyRepository.findById(id);
        if (optionalCodesCompany.isEmpty())
             return new ApiResponse("code not found ",false);
        boolean b = codesCompanyRepository.existsByCompanyCode(codesCompany.getCompanyCode());
        if (b) return new ApiResponse("Sorry.Code will be saved ",false);
        codesCompanyRepository.save(codesCompany);
        return new ApiResponse("successfully edited ",false);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable int id){
        boolean b = codesCompanyRepository.existsById(id);
        if (!b) return new ApiResponse("this code not found",false);
        codesCompanyRepository.deleteById(id);
        return new ApiResponse("successfully deleted",true);
    }



}
