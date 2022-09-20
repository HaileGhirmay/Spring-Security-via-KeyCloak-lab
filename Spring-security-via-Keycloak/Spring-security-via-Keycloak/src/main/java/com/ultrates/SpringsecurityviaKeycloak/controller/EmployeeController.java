package com.ultrates.SpringsecurityviaKeycloak.controller;

import com.ultrates.SpringsecurityviaKeycloak.entity.Employee;
import com.ultrates.SpringsecurityviaKeycloak.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;

    @GetMapping("/user/employee/{id}")
    @RolesAllowed("user") //this annotation defines that this method can be accessed by the use whose role is "user"
    public Employee getEmployee(@PathVariable Integer id){
        return   employeeService.getEmployee(id);

    }


    @GetMapping("/admin/employee")
    @RolesAllowed("admin") //This shows that this method can only be accessed by the user whose role is "admin"
    public List<Employee> getAllEmployees(){
        return employeeService.geAllEmployee();
    }
}

//note that inorder the above annotations work as expected , the configuration class should be annotated with all the necessary annotations that wraps this configurations.