package com.CustomerRelationshipManagement.repository;

import com.CustomerRelationshipManagement.entity.BroadcastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BroadcastRepository extends JpaRepository<BroadcastEntity, Long> {


    List<BroadcastEntity> findByScheduledTimeBeforeAndStatus(LocalDateTime time, String status);

    // 🔥 Add this for pending count
    long countByStatus(String status);
}
