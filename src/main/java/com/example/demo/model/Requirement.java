package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "requirements")
@Data
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;       // FK to users table
    private String subject;
    private String grade;
    private String board;

    @Column(length = 1000)
    private String description;

    private String slots;         // stored as comma-separated e.g. "Evening,Weekends"
    private String sessions;
    private String duration;
    private Integer budget;
    private String city;
    private String area;
    private String address;
    private String gender;        // any / male / female
    private String urgency;       // low / medium / high
    private String contactName;
    private String contactPhone;
    private String parentPhone;
    private Double lat;
    private Double lng;
    private String status;        // open / contacted / closed

    @Column(updatable = false)
    private LocalDateTime postedAt = LocalDateTime.now();
}
