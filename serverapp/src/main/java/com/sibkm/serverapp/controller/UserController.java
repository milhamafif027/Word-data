package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.User;
import com.sibkm.serverapp.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    // Get All
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    // Get by Id
    @GetMapping("{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    // // Create
    // @PostMapping
    // public User create(@RequestBody User user){
    //     return userService.create(user);
    // }

    // Update
    @PutMapping("{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    // Delete
    @DeleteMapping("{id}")
    public User delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
