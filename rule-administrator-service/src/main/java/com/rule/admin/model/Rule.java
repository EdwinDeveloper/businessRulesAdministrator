package com.rule.admin.model;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Rule {

    private UUID id;
    private UUID userId;
    private String conditions;

    private Map<String, Object> result;
    private String name;
    private String priority;
    private String nextTrue;
    private String nextFalse;

    public Rule(){
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId(){
        return userId;
    }

    public void setUserId(UUID userId){
        this.userId = userId;
    }
    public String getConditions() {
        return conditions;
    }
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Map<String, Object> getResult(){
        return result;
    }

    public void setResult(Map<String, Object> result){
        this.result = result;
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
