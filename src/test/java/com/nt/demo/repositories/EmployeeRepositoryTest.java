package com.nt.demo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nt.demo.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRepositoryTest
{

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void validateCRUD()
    {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("D");
        employee.setHireDate(new Date(System.currentTimeMillis()));
        employee.setSalary(1000);

        // Create
        Employee createdEmployee = repository.save(employee);
        assertNotNull(createdEmployee);
        assertNotNull(createdEmployee.getId());

        // Retrieve
        List<Employee> findAll = repository.findAll();
        assertEquals(1, findAll.size());

        // Update
        createdEmployee.setFirstName("Mark");
        Employee updatedEmployee = repository.save(createdEmployee);
        assertEquals("Mark", updatedEmployee.getFirstName());

        // Delete
        repository.delete(updatedEmployee.getId());

        // Retrieve
        findAll = repository.findAll();
        assertEquals(0, findAll.size());

    }

}
