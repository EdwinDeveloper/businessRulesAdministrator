package com.rule.admin.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;

import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Group {
    public Set<RuleEntity> rules;
    public String userId;
    public UUID id;
    public String groupName;
    public String runType;

    public Group() {
    }

    public Group(GroupEntity groupEntity){
        this.userId = groupEntity.getUserId();
        this.groupName = groupEntity.getGroupName();
        this.runType = groupEntity.getRunType();
        this.rules = groupEntity.getGroup_rules();
    }
}
