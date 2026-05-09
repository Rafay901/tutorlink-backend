package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromUserId;      // teacher's user id
    private String fromName;
    private Long toUserId;        // student's user id
    private String toName;
    private Long requirementId;
    private String subject;

    @Column(length = 2000)
    private String message;

    private Boolean isRead = false;

    @Column(updatable = false)
    private LocalDateTime sentAt = LocalDateTime.now();
}
