package com.masai.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.entity.Employee;
import com.masai.app.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class Controller {
    private final EmployeeRepository employeeRepository;

    public Controller(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/user")
    public ResponseEntity<String> commonResource() 
    {
        String msg = "Welcome user..! It's a non-secured resource";
       return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    @GetMapping("/secure/user")
    public ResponseEntity<String> securedResource() {
        String msg = "It's a secured resource";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<>("Welcome to Masai App for Admin", HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
}

