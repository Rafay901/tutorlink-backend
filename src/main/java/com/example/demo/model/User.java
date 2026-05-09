package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data // This automatically creates Getters and Setters
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName; 
    private String email;
    private String password;
    private String city;
    private String role; // Stores 'student', 'teacher', or 'admin'
}