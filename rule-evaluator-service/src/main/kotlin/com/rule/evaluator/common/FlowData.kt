package com.rule.evaluator.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.UUID

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Task(
    val rules: List<Rules>,
    val task: String,
)
data class Rules(
    val id:UUID,
    val userId:UUID,
    val name: String,
    val conditions: String,
    val priority: Int,
    val nextTrue: String? = null,
    val nextFalse: String? = null
)