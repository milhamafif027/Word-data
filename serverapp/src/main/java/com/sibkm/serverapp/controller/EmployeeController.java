package com.sibkm.serverapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sibkm.serverapp.entity.Employee;
import com.sibkm.serverapp.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    // Get All
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    // Get By Id
    @GetMapping("{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    // // Create
    // @PostMapping
    // public Employee create(@RequestBody Employee employee){
    //     return employeeService.create(employee);
    // }

    // Update
    @PutMapping("{id}")
    public Employee update(
            @PathVariable Integer id,
            @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    // Delete
    @DeleteMapping("{id}")
    public Employee delete(@PathVariable Integer id) {
        return employeeService.delete(id);
    }
}
