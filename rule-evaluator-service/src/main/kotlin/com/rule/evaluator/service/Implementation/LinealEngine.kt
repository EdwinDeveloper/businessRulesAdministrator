package com.rule.evaluator.service.Implementation

import com.rule.evaluator.common.Rules
import com.rule.evaluator.entity.EvaluatorEntity
import com.rule.evaluator.service.CoreEngine
import org.springframework.stereotype.Service

@Service
class LinealEngine: CoreEngine<Map<String, Any>, Map<String, Any>>() {
    override fun outputResult(
        input: Map<String, Any>,
        rules: Rules?,
        evaluatorEntity: EvaluatorEntity
    ): Map<String, Any> {
        var response: MutableMap<String, Any> = mutableMapOf()
        response.put("success", true)
        response.put("real", true)
        return response
    }
}