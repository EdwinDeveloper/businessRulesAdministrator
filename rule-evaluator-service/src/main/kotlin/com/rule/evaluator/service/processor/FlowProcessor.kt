package com.rule.evaluator.service.processor

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.EvaluatorEntity

interface FlowProcessor {
    fun processFlow(
        inputRequest: InputRequest,
        evaluatorEntity: EvaluatorEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Map<String, Any>
}