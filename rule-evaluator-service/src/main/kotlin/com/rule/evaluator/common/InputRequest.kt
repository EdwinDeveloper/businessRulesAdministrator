package com.rule.evaluator.common

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class InputRequest (
    var task: String? = null,
    var input: MutableMap<String, Any>,
    val traceabilityId: String
)