package com.CustomerRelationshipManagement.controller;

import com.CustomerRelationshipManagement.entity.BroadcastEntity;
import com.CustomerRelationshipManagement.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcast")
@CrossOrigin
public class BroadcastController {

    @Autowired
    private BroadcastService service;

    @PostMapping("/send")
    public BroadcastEntity sendMessage(@RequestBody BroadcastEntity message) {
        return service.sendMessage(message);
    }

    @GetMapping("/all")
    public List<BroadcastEntity> getAllMessages() {
        return service.getAllMessages();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable Long id) {
        service.deleteMessageById(id);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllNotifications() {
        service.deleteAllNotifications();
    }
    @GetMapping("/count")
    public long getMessageCount() {
        return service.getMessageCount();
    }
    @GetMapping("/pending/count")
    public long countPendingMessages() {
        return service.countPendingMessages();
    }





}
