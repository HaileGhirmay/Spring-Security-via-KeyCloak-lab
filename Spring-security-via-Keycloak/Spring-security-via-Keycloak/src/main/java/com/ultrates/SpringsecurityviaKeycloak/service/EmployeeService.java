package com.ultrates.SpringsecurityviaKeycloak.service;


import com.ultrates.SpringsecurityviaKeycloak.entity.Employee;
import com.ultrates.SpringsecurityviaKeycloak.repository.EmployeeRepository;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    //This annotation is used on a mthod to make  Any initialization after a dependency injection is done.
    //in this  class , once the Eployee repository got injected into the Employee service dependency which means it s object got created in the IOC.
    //THIS SERVICE CLASS WILL EXECUTE THIS METHOD to initialize the targeted table.

    @PostConstruct
    public void initializeEmployeeTable() {
        employeeRepository.saveAll(
                Stream.of(
                        new Employee("haile", 200.00),
                        new Employee("Amen", 2000.0),
                        new Employee("Aman ", 3003.00)
                        )
                        .collect(Collectors.toList()));
            }



//get employee by id
     public Employee getEmployee(Integer id){
        return


                employeeRepository.findById(id).orElse(null);
     }


     //findAll  employees in the employee table.
     public List<Employee> geAllEmployee(){
        return

                employeeRepository.findAll();
     }


    }

