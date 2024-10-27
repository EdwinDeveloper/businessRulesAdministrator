package com.rule.admin.Utils;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;
import com.rule.admin.model.Group;
import com.rule.admin.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Rule RuleFromEntity(RuleEntity ruleEntity){
        Rule ruleModel = new Rule();
        ruleModel.setId(ruleEntity.getId());
        ruleModel.setConditions(ruleEntity.getConditions());
        ruleModel.setResult(ruleEntity.getResult());
        ruleModel.setName(ruleEntity.getName());
        ruleModel.setPriority(ruleEntity.getPriority());
        ruleModel.setUserId(ruleEntity.getUserId());
        ruleModel.setNextFalse(ruleEntity.getNextFalse());
        ruleModel.setNextTrue(ruleEntity.getNextTrue());
        return ruleModel;
    }

    public static List<Rule> ListRulesFromListEntities(List<RuleEntity> ruleEntities){
        List<Rule> ruleList = new ArrayList<>();
        for(RuleEntity ruleEntity: ruleEntities){
            ruleList.add(RuleFromEntity(ruleEntity));
        }
        return ruleList;
    }

    public static RuleEntity EntityFromRule(Rule rule){
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setId(rule.getId());
        ruleEntity.setConditions(rule.getConditions());
        ruleEntity.setResult(rule.getResult());
        ruleEntity.setName(rule.getName());
        ruleEntity.setPriority(rule.getPriority());
        ruleEntity.setUserId(rule.getUserId());
        ruleEntity.setNextFalse(rule.getNextFalse());
        ruleEntity.setNextTrue(rule.getNextTrue());
        return ruleEntity;
    }

    public static RuleEntity EntityFromRuleAndEntity(Rule rule, RuleEntity oldEntity){
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setId( rule.getId() == null ? oldEntity.getId() : rule.getId());
        ruleEntity.setConditions(rule.getConditions() == null ? oldEntity.getConditions() : rule.getConditions());
        ruleEntity.setResult(rule.getResult() == null ? oldEntity.getResult() : rule.getResult());
        ruleEntity.setName(rule.getName() == null ? oldEntity.getName() : rule.getName());
        ruleEntity.setPriority(rule.getPriority() == null ? oldEntity.getPriority() : rule.getPriority());
        ruleEntity.setUserId(rule.getUserId() == null ? oldEntity.getUserId() : rule.getUserId());
        ruleEntity.setNextFalse(rule.getNextFalse() == null ? oldEntity.getNextFalse() : rule.getNextFalse());
        ruleEntity.setNextTrue(rule.getNextTrue() == null ? oldEntity.getNextTrue() : rule.getNextTrue());
        return ruleEntity;
    }

    public static List<RuleEntity> ListEntitiesFromListRules(List<Rule> rules){
        List<RuleEntity> rulesList = new ArrayList<>();
        for(Rule rule: rules){
            rulesList.add(EntityFromRule(rule));
        }
        return rulesList;
    }

    public static Group GroupFromEntity(GroupEntity groupEntity){
        Group group = new Group();
        group.setId(groupEntity.getId());
        group.setUserId(groupEntity.getUserId());
        group.setGroupName(groupEntity.getGroupName());
        group.setRunType(groupEntity.getRunType());
        group.setRules(ListRulesFromListEntities(groupEntity.getGroup_rules()));
        return group;
    }

    public static List<Group> ListGroupFromListEntities(List<GroupEntity> groupEntities){
        List<Group> groupList = new ArrayList<>();
        for(GroupEntity groupEntity: groupEntities){
            groupList.add(GroupFromEntity(groupEntity));
        }
        return groupList;
    }

    public static GroupEntity EntityFromGroup(Group group){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(group.getId());
        groupEntity.setUserId(group.getUserId());
        groupEntity.setGroupName(group.getGroupName());
        groupEntity.setRunType(group.getRunType());
        groupEntity.setGroup_rules(Mapper.ListEntitiesFromListRules(group.getRules()));
        return groupEntity;
    }

    public static List<GroupEntity> ListEntitiesFromListGroup(List<Group> groups){
        List<GroupEntity> groupsEntities = new ArrayList<>();
        for(Group group: groups){
            groupsEntities.add(EntityFromGroup(group));
        }
        return groupsEntities;
    }

}
