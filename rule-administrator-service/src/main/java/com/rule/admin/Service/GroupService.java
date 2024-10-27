package com.rule.admin.Service;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Entity.RuleEntity;
import com.rule.admin.Exception.RAException;
import com.rule.admin.Repository.GroupRepository;
import com.rule.admin.Repository.RuleRepository;
import com.rule.admin.Utils.Mapper;
import com.rule.admin.model.Group;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RuleRepository ruleRepository;

    public List<Group> getGroups(){
         return Mapper.ListGroupFromListEntities(groupRepository.findAll());
    }

    public Group findGroupById(UUID GroupId){
        Optional<GroupEntity> groupEntity = groupRepository.findById(GroupId);
        if(groupEntity.isPresent()){
            return Mapper.GroupFromEntity(groupEntity.get());
        }else{
            logger.error("--RULE-ADMIN-SERVICE FIND GROUP BY ID --error [{}]", "GROUP NOT FOUND");
            throw new RAException(HttpStatus.BAD_REQUEST, "400", "GROUP NOT FOUND");
        }
    }

    public List<Group> findGroupByUserId(UUID userId){
        List<GroupEntity> groupListEntity = groupRepository.findByUserId(userId);
        return Mapper.ListGroupFromListEntities(groupListEntity);
    }

    @Transactional
    public Group UpdateGroup(Group group){
        Optional<GroupEntity> groupEntity = groupRepository.findById(group.getId());
        if(groupEntity.isEmpty()){
            throw new RAException(HttpStatus.BAD_REQUEST, "400", "GROUP DOESN'T EXIST");
        }

        try{
            group.getRules().forEach(rule -> {
                Optional<RuleEntity> ruleToUpdate = ruleRepository.findById(rule.getId());
                ruleToUpdate.ifPresent(ruleEntity -> ruleRepository.save(Mapper.EntityFromRuleAndEntity(rule, ruleEntity)));
            });
            return Mapper.GroupFromEntity(groupRepository.save(Mapper.EntityFromGroup(group)));
        }catch (Exception e){
            logger.error("--RULE-ADMIN-SERVICE CREATE GROUP --error [{}]", e.getMessage());
            throw new RAException(HttpStatus.BAD_REQUEST, "400", e.getMessage(), e);
        }
    }

    @Transactional
    public Group CreateGroup(Group group){
        Optional<GroupEntity> groupEntity = groupRepository.findByGroupName(group.getGroupName());
        if(groupEntity.isPresent()){
            throw new RAException(HttpStatus.BAD_REQUEST, "400", "GROUP ALREADY EXIST");
        }
        group.getRules().forEach(rule -> {
            ruleRepository.save(Mapper.EntityFromRule(rule));
        });
        return Mapper.GroupFromEntity(groupRepository.save(Mapper.EntityFromGroup(group)));
    }
}
