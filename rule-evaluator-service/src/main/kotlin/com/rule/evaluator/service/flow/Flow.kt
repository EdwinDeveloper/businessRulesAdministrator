package com.rule.evaluator.service.flow

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.EvaluatorEntity
import com.rule.evaluator.service.processor.FlowProcessor
import com.rule.evaluator.util.toJsonString
import org.slf4j.LoggerFactory

abstract class Flow<PROCESS_DATA>: FlowProcessor {

    private val logger = LoggerFactory.getLogger(this::class.java)
    protected abstract fun getExternalInfo(inputRequest: InputRequest): PROCESS_DATA
    protected abstract fun completeEvaluation(requestData: Map<String, Any>, evaluatorEntity: EvaluatorEntity, rules: List<Rules>, runType: TypeFlow): Map<String, Any>

    override fun processFlow(
        inputRequest: InputRequest,
        evaluatorEntity: EvaluatorEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Map<String, Any> {
        logger.info("RULE VALIDATOR -- running -- [{}]", evaluatorEntity.traceabilityId)
        return this.completeEvaluation(inputRequest.input, evaluatorEntity, rules, runType)
    }
}