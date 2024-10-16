package com.rule.admin.Service;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Exception.RAException;
import com.rule.admin.Repository.GroupRepository;
import com.rule.admin.model.Group;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    public GroupEntity findGroupById(Long GroupId){
        Optional<GroupEntity> groupEntity = groupRepository.findById(GroupId);
        return groupEntity.get();
    }

    public GroupEntity createGroup(String user, Group group){
        GroupEntity groupEntity = new GroupEntity();
        try{
            groupEntity.setUserId(user);
            groupEntity.setGroupName(group.groupName);
            groupEntity.setRunType(group.runType);
            groupEntity.setGroup_rules(Collections.emptySet());
            groupRepository.save(groupEntity);
        }catch (Exception e){
            logger.error("--RULE-ADMIN-SERVICE CREATE GROUP --error [{}]", e.getMessage());
            throw new RAException(HttpStatus.BAD_REQUEST, "400", e.getMessage(), e);
        }
        return groupEntity;
    }

}
