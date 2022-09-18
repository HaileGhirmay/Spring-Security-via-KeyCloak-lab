package com.ultrates.SpringsecurityviaKeycloak.controller;

import com.ultrates.SpringsecurityviaKeycloak.entity.Employee;
import com.ultrates.SpringsecurityviaKeycloak.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    //restApi to save Employee to data base

    @PostMapping("/admin/saveemployee")
    public ResponseEntity<Employee>save(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepository.save(employee));

    }

    @GetMapping("/user/employee/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        return   employeeRepository.findById(id).orElse(null );

    }
}
