package com.rule.evaluator.common

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Task(
    val rules: List<Rules>,
    val task: String,
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Rules(
    val id:String,
    val conditions: String,
    val action: Map<String, Any?>,
    val nextTrue: String? = null,
    val nextFalse: String? = null
)