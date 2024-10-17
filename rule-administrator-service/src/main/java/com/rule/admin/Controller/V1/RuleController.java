package com.rule.admin.Controller.V1;

import com.rule.admin.Service.RuleService;
import com.rule.admin.Utils.Route;
import com.rule.admin.model.Group;
import com.rule.admin.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Route.V1 + Route.RULE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/Create")
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule){
        return new ResponseEntity<>(new Rule(ruleService.createRule(rule)), HttpStatus.OK) ;
    }

}
