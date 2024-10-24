package com.rule.admin.Controller.V1;

import com.rule.admin.Service.GroupService;
import com.rule.admin.Utils.Route;
import com.rule.admin.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = Route.V1 + Route.GROUP, produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<Group>> getGroups(){
        return new ResponseEntity<>(groupService.getGroups(), HttpStatus.OK);
    }

    @GetMapping("/User/{userId}")
    public ResponseEntity<List<Group>> getGroupsByUser(@PathVariable UUID userId){
        return new ResponseEntity<>(groupService.findGroupByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{idGroup}")
    public ResponseEntity<Group> getRules(
            @PathVariable UUID idGroup
    ){
        logger.info("--RULE-ADMIN-SERVICE GET GROUP --idGroup [{}]", idGroup);
        return new ResponseEntity<>(groupService.findGroupById(idGroup), HttpStatus.OK) ;
    }

    @PostMapping("/{user}")
    public ResponseEntity<Group> createGroup(@PathVariable String user, @RequestBody Group group){
        logger.info("--RULE-ADMIN-SERVICE CREATE GROUP --user [{}]", user);
        return new ResponseEntity<>(groupService.createOrUpdateGroup(user, group), HttpStatus.CREATED) ;
    }

    @PutMapping("/update")
    public ResponseEntity<Group> updateGroupRules(@RequestBody Group group){
        logger.info("--RULE-ADMIN-SERVICE UPDATE GROUP RULES --group [{}]", group.getGroupName());
        return new ResponseEntity<>(groupService.updateGroupById(group), HttpStatus.NO_CONTENT);
    }

}
