package com.sibkm.serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sibkm.serverapp.entity.User;
import com.sibkm.serverapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    // Get All
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Get By Id
    public User getById(Integer Id) {
        return userRepository
                .findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!"));
    }

    // // Create
    // public User create(User user){
    //     return userRepository.save(user);
    // }

    // Update
    public User update(Integer id, User user) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    // Delete
    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
