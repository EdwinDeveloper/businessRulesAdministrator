package com.rule.admin.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class RuleEntity {

    @Id
    @GeneratedValue
    @Column(name = "rule_id", unique = true, nullable = false, updatable = false)
     private String id = UUID.randomUUID().toString();

}
