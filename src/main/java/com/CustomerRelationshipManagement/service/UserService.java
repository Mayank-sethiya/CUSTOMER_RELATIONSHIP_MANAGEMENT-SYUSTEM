

package com.CustomerRelationshipManagement.service;

import com.CustomerRelationshipManagement.entity.UserEntity;
import com.CustomerRelationshipManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserEntity registerUser(UserEntity user) {
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



}









