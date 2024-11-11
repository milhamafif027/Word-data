package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.Role;
import com.sibkm.serverapp.service.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    // Get All
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    // Get by Id
    @GetMapping("{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    // Create
    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    // Update
    @PutMapping("{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    // Delete
    @DeleteMapping("{id}")
    public Role delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }
}