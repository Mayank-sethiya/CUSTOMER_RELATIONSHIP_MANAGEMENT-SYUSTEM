package com.CustomerRelationshipManagement.service;

import com.CustomerRelationshipManagement.entity.BroadcastEntity;
import com.CustomerRelationshipManagement.repository.BroadcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BroadcastService {

    @Autowired
    private BroadcastRepository repository;

    public BroadcastEntity sendMessage(BroadcastEntity message) {
        message.setCreatedAt(LocalDateTime.now());

        if (message.getScheduledTime() != null && message.getScheduledTime().isAfter(LocalDateTime.now())) {
            message.setStatus("Pending");
        } else {
            message.setStatus("Sent");
        }

        return repository.save(message);
    }

    @Transactional
    @Scheduled(fixedRate = 10000)
    public void processScheduledMessages() {
        LocalDateTime now = LocalDateTime.now();
        List<BroadcastEntity> pendingMessages = repository.findByScheduledTimeBeforeAndStatus(now, "Pending");

        for (BroadcastEntity msg : pendingMessages) {
            msg.setStatus("Sent");
            repository.save(msg);
            System.out.println("Updated status to Sent for ID: " + msg.getId());
        }
    }

    public List<BroadcastEntity> getAllMessages() {
        return repository.findAll();
    }

    @Transactional
    public void deleteMessageById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAllMessages() {
        repository.deleteAll();
    }

    public long getMessageCount() {
        return repository.count();
    }

    public long countPendingMessages() {
        return repository.countByStatus("Pending");
    }
}
