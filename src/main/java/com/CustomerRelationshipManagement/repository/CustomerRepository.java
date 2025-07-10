package com.CustomerRelationshipManagement.repository;

import com.CustomerRelationshipManagement.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // Custom queries (if needed) can be added here

    // Example of a custom query to find by email
    // Optional<CustomerEntity> findByEmail(String email);
}
