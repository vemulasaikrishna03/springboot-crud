package com.project.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
