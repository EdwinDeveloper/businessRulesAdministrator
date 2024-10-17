package com.rule.admin.Service;

import com.rule.admin.Entity.RuleEntity;
import com.rule.admin.Exception.RAException;
import com.rule.admin.Repository.RuleRepository;
import com.rule.admin.model.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);

    @Autowired
    private RuleRepository ruleRepository;

    public RuleEntity createRule(Rule rule){
        RuleEntity ruleEntity = new RuleEntity();
        try{
            ruleEntity.setConditions(rule.getConditions());
            ruleEntity.setName(rule.getName());
            ruleEntity.setPriority(rule.getPriority());
            ruleEntity.setNextTrue(rule.getNextTrue());
            ruleEntity.setNextFalse(rule.getNextFalse());
            ruleRepository.save(ruleEntity);
        }catch (RAException e){
            logger.error("--RULE-ADMIN-SERVICE CREATE RULE --error [{}]", e.getMessage());
            throw new RAException(HttpStatus.BAD_REQUEST, "400", e.getMessage(), e);
        }
        return ruleEntity;
    }

}
