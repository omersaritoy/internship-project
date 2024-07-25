package com.example.Example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "logs")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entityType;
    private String action;
    private LocalDateTime timestamp;
    private String details;
    private Long entityId;

    public Logger(String action, String details, String entityType, LocalDateTime timestamp, Long entityId) {
        this.action = action;
        this.details = details;
        this.entityType = entityType;
        this.timestamp = timestamp;
        this.entityId = entityId;
    }


}
