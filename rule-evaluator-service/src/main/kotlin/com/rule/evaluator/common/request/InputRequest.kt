package com.rule.evaluator.common.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.UUID

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class InputRequest (
    val task: String? = null,
    val user: String,
    var input: MutableMap<String, Any>,
    val traceabilityId: UUID
)