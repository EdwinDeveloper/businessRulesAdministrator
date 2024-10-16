package com.rule.admin.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;

import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupResponse {
    Set<RuleEntity> rules;
    String userId;
    String id;
    String groupName;
    String runType;

    public GroupResponse(GroupEntity groupEntity){
        this.id = groupEntity.id;
        this.userId = groupEntity.userId;
        this.groupName = groupEntity.groupName;
        this.runType = groupEntity.runType;
        this.rules = groupEntity.rules;
    }
}
