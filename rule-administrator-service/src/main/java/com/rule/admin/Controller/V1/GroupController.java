package com.rule.admin.Controller.V1;

import com.rule.admin.model.GroupResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/V1", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    @PostMapping("/{user}")
    public ResponseEntity<GroupResponse> getRules(){
        return null;
    }

}
