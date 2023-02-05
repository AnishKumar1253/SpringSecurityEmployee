package com.masai.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}

