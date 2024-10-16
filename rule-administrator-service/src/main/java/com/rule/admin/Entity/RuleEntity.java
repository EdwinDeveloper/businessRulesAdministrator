package com.rule.admin.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "rule")  // Correct table name for RuleEntity
public class RuleEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID id;  // Removed UUID.randomUUID() initialization

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private String priority;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "group_rules")  // Ensure this matches GroupEntity
    @JsonIgnoreProperties("group_rules")
    private Set<GroupEntity> group_rules;

    @Column(name = "next_true")
    private String nextTrue;

    @Column(name = "next_false")
    private String nextFalse;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<GroupEntity> getGroup_rules() {
        return group_rules;
    }

    public void setGroup_rules(Set<GroupEntity> group_rules) {
        this.group_rules = group_rules;
    }

    public String getNextTrue() {
        return nextTrue;
    }

    public void setNextTrue(String nextTrue) {
        this.nextTrue = nextTrue;
    }

    public String getNextFalse() {
        return nextFalse;
    }

    public void setNextFalse(String nextFalse) {
        this.nextFalse = nextFalse;
    }
}
