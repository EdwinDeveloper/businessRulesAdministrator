package com.rule.admin.Controller.V1;

import com.rule.admin.Service.RuleService;
import com.rule.admin.Utils.Route;
import com.rule.admin.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Route.V1 + Route.RULE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping
    public ResponseEntity<List<Rule>> getGroups(){
        return new ResponseEntity<>(ruleService.getRules(), HttpStatus.OK);
    }

    @PostMapping("/Create")
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule){
        return new ResponseEntity<>(ruleService.createRule(rule), HttpStatus.OK) ;
    }

}
