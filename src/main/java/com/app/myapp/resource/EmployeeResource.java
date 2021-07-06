package com.app.myapp.resource;

import com.app.myapp.model.Employee;
import com.app.myapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeResource {
    private final EmployeeService employeeService;

    @GetMapping("/getEmployeeInfo/{companyId}/{employeeId}")
    public Employee getEmployeeInfo(@PathVariable Long companyId, @PathVariable Long employeeId) {
        return employeeService.getByCompanyAndEmployeeId(companyId, employeeId);
    }
}
