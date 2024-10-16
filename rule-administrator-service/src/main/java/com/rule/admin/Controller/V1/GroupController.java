package com.rule.admin.Controller.V1;

import com.rule.admin.Service.GroupService;
import com.rule.admin.model.Group;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/V1", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    //private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @GetMapping("/{user}/{idGroup}")
    public ResponseEntity<Group> getRules(
            @PathVariable String user,
            @PathVariable Long idGroup
    ){
        //logger.info("--RULE-ADMIN-SERVICE GET GROUP --user [{}] --idGroup [{}]", user, idGroup);
        return new ResponseEntity<>(new Group(groupService.findGroupById(idGroup)), HttpStatus.OK) ;
    }

    @PostMapping("/{user}")
    public ResponseEntity<Group> createGroup(@PathVariable String user, @RequestBody Group group){
        //logger.info("--RULE-ADMIN-SERVICE CREATE GROUP --user [{}]", user);
        return new ResponseEntity<>(new Group(groupService.createGroup(user, group)), HttpStatus.CREATED) ;
    }

}
