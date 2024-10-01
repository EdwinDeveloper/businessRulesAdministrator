package com.rule.evaluator.service.processor

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.entity.RunEntity

interface FlowProcessor {
    fun processFlow(requestData: Map<String, Any>, runEntity: RunEntity, rules: List<Rules>, runType: TypeFlow): Any
}