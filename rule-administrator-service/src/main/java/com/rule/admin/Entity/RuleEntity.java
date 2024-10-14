package com.rule.admin.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rule")
public class RuleEntity {

    @Id
    @GeneratedValue
    @Column(name = "rule_id", unique = true, nullable = false, updatable = false)
     private String id = UUID.randomUUID().toString();

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private String priority;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt  = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "next_true")
    private String nextTrue;

    @Column(name = "next_false")
    private String nextFalse;
}
