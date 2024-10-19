package com.rule.evaluator.common.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import java.util.UUID

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RuleAdminResponse(
    val id: UUID,
    val rules: List<Rules>,
    val userId: UUID,
    val GroupName: String,
    val runType: TypeFlow
)
