package com.rule.admin.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "group")
public class GroupEntity {

    @Id
    @GeneratedValue
    @Column(name = "group_id", unique = true, nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_rule",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<RuleEntity> rulesGroup;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt  = Timestamp.valueOf(LocalDateTime.now());

}
