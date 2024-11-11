package com.sibkm.serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sibkm.serverapp.entity.Employee;
import com.sibkm.serverapp.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    
    // Get All
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // Get By Id
    public Employee getById(Integer Id) {
        return employeeRepository
                .findById(Id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found!!!"));
    }

    // // Create
    // public Employee create(Employee employee) {
    //     return employeeRepository.save(employee);
    // }

    // Update
    public Employee update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    // Delete
    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
