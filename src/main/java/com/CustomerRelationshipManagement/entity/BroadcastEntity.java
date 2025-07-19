    package com.CustomerRelationshipManagement.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDateTime;
    import java.util.List;

    @Entity
    @Table(name = "broadcast_messages")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class BroadcastEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String type;

        @ElementCollection
        @CollectionTable(name = "broadcast_recipients", joinColumns = @JoinColumn(name = "broadcast_id"))
        @Column(name = "recipient")
        private List<String> recipients;

        private String subject;

        @Lob
        @Basic(fetch = FetchType.EAGER) // Force eager loading of LOB
        @Column(length = 10000)
        private String content;


        private LocalDateTime scheduledTime;
        private LocalDateTime createdAt;
        private String status; // Add this field to track "Sent" or "Scheduled"

    }
