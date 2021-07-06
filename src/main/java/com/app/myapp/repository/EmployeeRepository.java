package com.app.myapp.repository;

import com.app.myapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.companyId = :companyId")
    List<Employee> findByCompanyId(@Param("companyId") Long companyId);

    Optional<Employee> findByCompanyIdAndId(Long companyId, Long id);
}
