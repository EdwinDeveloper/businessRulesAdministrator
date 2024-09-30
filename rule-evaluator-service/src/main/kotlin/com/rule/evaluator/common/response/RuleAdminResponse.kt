package com.rule.evaluator.common.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RuleAdminResponse(
    val rules: List<Rules>,
    val userId: String,
    val flowId: Long? = null,
    val flowName: String? = null,
    val runType: TypeFlow
)
