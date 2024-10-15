package com.rule.admin.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.tomcat.util.digester.Rules;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupResponse {
    List<Rules> rules;
    String userId;
    String groupId;
    String GroupName;
    String runType;
}
