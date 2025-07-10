package com.CustomerRelationshipManagement.controller;

import com.CustomerRelationshipManagement.entity.AdminEntity;
import com.CustomerRelationshipManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public AdminEntity register(@RequestBody AdminEntity user) {
        return adminService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminEntity user) {
        AdminEntity admin = adminService.login(user.getUsername(), user.getPassword());
        if (admin != null) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin credentials are wrong");
        }
    }
}
