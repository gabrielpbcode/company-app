package com.app.myapp;

import com.app.myapp.model.Company;
import com.app.myapp.model.Employee;
import com.app.myapp.model.Initializer;
import com.app.myapp.repository.CompanyRepository;
import com.app.myapp.repository.EmployeeRepository;
import com.app.myapp.repository.InitializerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final InitializerRepository initializerRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void run(String... args) throws Exception {
        Initializer init = initializerRepository.findByValue("configured");

        if (init == null || !init.isInitialized()) {
            createEmployees();
            createCompanies();

            if (init == null) {
                init = new Initializer();
                init.setValue("configured");
                initializerRepository.save(init);
            }
        }

        init.setInitialized(true);
    }

    private void createCompanies() {
        companyRepository.deleteAll();

        Employee roberto = employeeRepository.findEmployeeByName("Roberto Sousa").get();
        Employee ximenes = employeeRepository.findEmployeeByName("Ximenes da Silva").get();
        Employee diogenes = employeeRepository.findEmployeeByName("D'Alessandro Diógenes").get();

        Company peleCompany = new Company("Edson Arandes do Nascimento", "Pele Sports", new BigDecimal(5000));
        peleCompany.setEmployees(List.of(roberto, diogenes));
        roberto.setCompany(peleCompany);
        diogenes.setCompany(peleCompany);

        Company johnSnow = new Company("João das Neves", "Snow Consultoria dos Bastardos LTDA", new BigDecimal(10000));
        johnSnow.setEmployees(List.of(ximenes));
        ximenes.setCompany(johnSnow);

        companyRepository.saveAll(List.of(peleCompany, johnSnow));
    }

    private void createEmployees() {
        employeeRepository.deleteAll();

        Calendar c = Calendar.getInstance();

        c.set(2000, Calendar.NOVEMBER, 11);
        Employee roberto = new Employee("Roberto Sousa", "321.654.987-11", c.getTime(), new BigDecimal(2000));

        c.set(1995, Calendar.JANUARY, 15);
        Employee ximenes = new Employee("Ximenes da Silva", "321.654.987-20", c.getTime(), new BigDecimal(1200));

        c.set(2001, Calendar.NOVEMBER, 22);
        Employee diogenes = new Employee("D'Alessandro Diógenes", "654-321-789-13", c.getTime(), new BigDecimal(3000));

        employeeRepository.saveAll(List.of(roberto, ximenes, diogenes));
    }
}
