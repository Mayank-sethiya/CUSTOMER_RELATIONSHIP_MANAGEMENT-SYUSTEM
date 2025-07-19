package com.CustomerRelationshipManagement.repository;

import com.CustomerRelationshipManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<UserEntity> searchUsers(@Param("keyword") String keyword);

    long countByCreatedAtAfter(LocalDateTime time);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByPhonenumber(String phonenumber);





}

