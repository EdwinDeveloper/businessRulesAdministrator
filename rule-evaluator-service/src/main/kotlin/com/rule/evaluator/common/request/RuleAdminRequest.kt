package com.rule.evaluator.common.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RuleAdminRequest(
    val task: String? = null,
    val user: String,
    val traceabilityId: String
)
