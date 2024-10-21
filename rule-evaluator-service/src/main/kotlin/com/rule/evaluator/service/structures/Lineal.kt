package com.rule.evaluator.service.structures

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.EvaluatorEntity
import com.rule.evaluator.service.Implementation.LinealEngine
import com.rule.evaluator.service.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class Lineal : Flow<Map<String, Any>>() {

    @Autowired
    private lateinit var linealEngine: LinealEngine

    override fun getExternalInfo(inputRequest: InputRequest): Map<String, Any> {
        TODO("Not yet implemented")
    }

    override fun completeEvaluation(
        requestData: Map<String, Any>,
        evaluatorEntity: EvaluatorEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Map<String, Any> {
        var response = linealEngine.ruleGroupExecution(rules, requestData, evaluatorEntity)
        return response
    }
}