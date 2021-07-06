package com.app.myapp.service;

import com.app.myapp.exception.CompanyNotFoundException;
import com.app.myapp.model.Company;
import com.app.myapp.model.Employee;
import com.app.myapp.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeService employeeService;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public List<Employee> getAllEmployees(Long id) {
        return employeeService.getByCompanyId(id);
    }

    public Company payEmployees(Long companyId) throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findById(companyId);

        if (company.isPresent()) {
            return company.get();
        }

        throw new CompanyNotFoundException("Não foi possível encontrar a Empresa buscada");
    }
}
