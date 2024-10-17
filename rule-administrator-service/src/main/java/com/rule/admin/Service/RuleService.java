package com.rule.admin.Service;

import com.rule.admin.Entity.RuleEntity;
import com.rule.admin.Exception.RAException;
import com.rule.admin.Repository.RuleRepository;
import com.rule.admin.Utils.Mapper;
import com.rule.admin.model.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RuleService {

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);

    @Autowired
    private RuleRepository ruleRepository;

    public Rule createRule(Rule rule){
        try{
            return Mapper.RuleFromEntity(ruleRepository.save(Mapper.EntityFromRule(rule)));
        }catch (RAException e){
            logger.error("--RULE-ADMIN-SERVICE CREATE RULE --error [{}]", e.getMessage());
            throw new RAException(HttpStatus.BAD_REQUEST, "400", e.getMessage(), e);
        }
    }

    public List<Rule> getRules(){
        return Mapper.ListRulesFromListEntities(ruleRepository.findAll());
    }

}
