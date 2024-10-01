package com.rule.evaluator.service.processor

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.RunEntity
import java.util.*

interface FlowProcessor {
    fun processFlow(
        inputRequest: InputRequest,
        runEntity: RunEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Any
}