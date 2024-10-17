package com.rule.admin.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;

import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Group {
    private Set<RuleEntity> rules;
    private String userId;
    private UUID id;
    private String groupName;
    private String runType;

    public Group() {
    }

    public Group(GroupEntity groupEntity){
        this.setUserId(groupEntity.getUserId());
        this.setGroupName(groupEntity.getGroupName());
        this.setRunType(groupEntity.getRunType());
        this.setRules(groupEntity.getGroup_rules());
    }


    public Set<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(Set<RuleEntity> rules) {
        this.rules = rules;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
