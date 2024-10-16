package com.rule.admin.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rule.admin.model.Group;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"group\"")  // Escaped because 'group' is a reserved keyword in SQL
public class GroupEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID id;  // Removed UUID.randomUUID() initialization

    @Column(name = "user_id")
    private String userId;

    @Column(name = "group_name", unique = true)
    private String groupName;

    @Column(name = "run_type")
    private String runType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_rules",  // Ensure this table exists with the correct foreign key constraints
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")  // Ensure this matches RuleEntity
    )
    private Set<RuleEntity> group_rules;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public Set<RuleEntity> getGroup_rules() {
        return group_rules;
    }

    public void setGroup_rules(Set<RuleEntity> group_rules) {
        this.group_rules = group_rules;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
