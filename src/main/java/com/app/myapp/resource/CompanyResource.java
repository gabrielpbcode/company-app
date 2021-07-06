package com.app.myapp.resource;

import com.app.myapp.exception.CompanyNotFoundException;
import com.app.myapp.model.Company;
import com.app.myapp.model.Employee;
import com.app.myapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyResource {
    private final CompanyService companyService;

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/getEmployeesByCompanyId/{id}")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Long id) {
        return companyService.getAllEmployees(id);
    }

    @GetMapping("/payEmployees/{companyId}")
    public Company payEmployees(@PathVariable Long companyId) throws CompanyNotFoundException {
        return companyService.payEmployees(companyId);
    }
}