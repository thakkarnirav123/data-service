package com.nt.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

    public Employee findById(@Param("id") @RequestParam("id") Long id);
}
