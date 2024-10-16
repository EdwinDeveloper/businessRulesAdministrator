package com.rule.admin.Service;

import com.rule.admin.Entity.GroupEntity;
import com.rule.admin.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupEntity findGroupById(Long GroupId){
        Optional<GroupEntity> groupEntity = groupRepository.findById(GroupId);
        return groupEntity.get();
    }

}
