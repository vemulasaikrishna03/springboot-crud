package com.project.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crud.entity.Employee;
import com.project.crud.repository.EmployeeRepository;
import java.util.*;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee){
        return repository.save(employee);
        
    }

    public List<Employee> saveEmployees(List<Employee> employees){
        return repository.saveAll(employees);
        
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
        
    }

    public Employee getEmployeeById(int id){
        return repository.findById(id).orElse(null);
        
    }

    public String deleteEmployee(int id){
        repository.deleteById(id);
        return "Employee removed"+id;
    }

    public Employee updateEmployee(Employee employee){
        Employee existingEmployee=repository.findById(employee.getId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmailId(employee.getEmailId());

        return repository.save(existingEmployee);
    }
    
}
