

package com.CustomerRelationshipManagement.service;

import com.CustomerRelationshipManagement.entity.UserEntity;
import com.CustomerRelationshipManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;



    public UserEntity registerUser(UserEntity user) {
        String email = user.getEmail();
        String username = user.getUsername();

        // Email validations
        if (!email.endsWith("@gmail.com")) {
            throw new RuntimeException("Only @gmail.com emails are allowed");
        }
        if (email.contains(" ")) {
            throw new RuntimeException("Email cannot contain spaces");
        }
        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        // Username validations
        if (username.contains(" ")) {
            throw new RuntimeException("Username cannot contain spaces");
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new RuntimeException("Username can only contain letters, numbers, and underscores");
        }
        if (userRepo.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepo.existsByPhonenumber(user.getPhonenumber())) {
            throw new RuntimeException("Phone number already exists");
        }


        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("inactive");

        return userRepo.save(user);
    }



    public UserEntity loginUser(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        Optional<UserEntity> existingUserOpt = userRepo.findById(id);
        if (existingUserOpt.isPresent()) {
            UserEntity existingUser = existingUserOpt.get();

            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhonenumber(updatedUser.getPhonenumber());
            existingUser.setLocation(updatedUser.getLocation());

            // Optional: only update password if provided
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(updatedUser.getPassword());
            }

            return userRepo.save(existingUser);
        } else {
            return null;
        }
    }






    public List<UserEntity> searchUsers(String keyword) {
        return userRepo.searchUsers(keyword);
    }
    public long countUsers() {
        return userRepo.count();
    }

    public Map<String, Object> getMonthlySignupData() {
        List<UserEntity> allUsers = userRepo.findAll();
        Map<String, Integer> monthCountMap = new LinkedHashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");

        // Init map with zero for 12 months
        for (int i = 0; i < 12; i++) {
            LocalDate date = LocalDate.now().minusMonths(11 - i);
            monthCountMap.put(date.format(formatter), 0);
        }

        for (UserEntity user : allUsers) {
            LocalDate createdMonth = user.getCreatedAt().toLocalDate().withDayOfMonth(1);
            String monthLabel = createdMonth.format(formatter);
            if (monthCountMap.containsKey(monthLabel)) {
                monthCountMap.put(monthLabel, monthCountMap.get(monthLabel) + 1);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("months", new ArrayList<>(monthCountMap.keySet()));
        response.put("counts", new ArrayList<>(monthCountMap.values()));
        return response;
    }




}









