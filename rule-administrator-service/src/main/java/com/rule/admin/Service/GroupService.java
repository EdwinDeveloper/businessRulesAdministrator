package com.rule.admin.Service;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Repository.GroupRepository;
import com.rule.admin.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupEntity findGroupById(Long GroupId){
        Optional<GroupEntity> groupEntity = groupRepository.findById(GroupId);
        return groupEntity.get();
    }

    public GroupEntity createGroup(String user, Group group){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setUserId(user);
        groupEntity.setGroupName(group.groupName);
        groupEntity.setRunType(group.runType);
        //groupEntity.rules = Collections.emptySet();
        groupRepository.save(groupEntity);
        return groupEntity;
    }

}
