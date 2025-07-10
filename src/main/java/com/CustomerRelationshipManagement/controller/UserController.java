package com.CustomerRelationshipManagement.controller;

import com.CustomerRelationshipManagement.entity.UserEntity;
import com.CustomerRelationshipManagement.repository.UserRepository;
import com.CustomerRelationshipManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user) {
        UserEntity existing = userService.loginUser(user.getUsername(), user.getPassword());
        if (existing != null) {
            existing.setStatus("active");  // ✅ Set status to active
            userRepository.save(existing); // ✅ Save update
            return "Login successful! Welcome " + existing.getUsername();
        } else {
            return "Login failed. Invalid credentials.";
        }
    }
    @PostMapping("/logout")
    public String logout(@RequestBody UserEntity user) {
        UserEntity existing = userRepository.findByUsername(user.getUsername());
        if (existing != null) {
            existing.setStatus("inactive");
            userRepository.save(existing);
            return "Logout successful!";
        } else {
            return "Logout failed.";
        }
    }



    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    // DELETE user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // UPDATE user
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/search")
    public List<UserEntity> search(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }
    @GetMapping("/count")
    public long getUserCount() {
        return userService.countUsers();
    }





}


