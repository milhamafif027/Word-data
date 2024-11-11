package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Role;
import com.sibkm.serverapp.repository.RoleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    // Get All
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    // Get By Id
    public Role getById(Integer Id) {
        return roleRepository
                .findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!!!"));
    }

    // Create
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    // Update
    public Role update(Integer id, Role role) {
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

    // Delete
    public Role delete(Integer id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}