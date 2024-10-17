package com.rule.admin.model;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public class Rule {

    private UUID id;
    private String conditions;
    private String name;
    private String priority;
    private String nextTrue;
    private String nextFalse;

    public Rule(){
    }

    public Rule(RuleEntity ruleEntity){
        this.id = ruleEntity.getId();
        this.conditions = ruleEntity.getConditions();
        this.name = ruleEntity.getName();
        this.priority = ruleEntity.getPriority();
        this.nextTrue = ruleEntity.getNextTrue();
        this.nextFalse = ruleEntity.getNextFalse();
    }

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
