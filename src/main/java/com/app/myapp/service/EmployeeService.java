package com.app.myapp.service;

import com.app.myapp.model.Employee;
import com.app.myapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee getByCompanyAndEmployeeId(Long companyId, Long employeeId) {
        return employeeRepository.findByCompanyIdAndId(companyId, employeeId).orElse(null);
    }

    public List<Employee> getByCompanyId(Long companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }
}
