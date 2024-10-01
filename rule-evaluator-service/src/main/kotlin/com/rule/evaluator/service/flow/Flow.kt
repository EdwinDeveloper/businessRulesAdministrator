package com.rule.evaluator.service.flow

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.RunEntity
import com.rule.evaluator.service.processor.FlowProcessor
import com.rule.evaluator.util.toJsonString
import org.slf4j.LoggerFactory
import java.util.*

abstract class Flow<PROCESS_DATA, OUTPUT_DATA>: FlowProcessor {

    private val logger = LoggerFactory.getLogger(this::class.java)
    protected abstract fun getExternalInfo(inputRequest: InputRequest): PROCESS_DATA
    protected abstract fun completeEvaluation(requestData: Map<String, Any>, runEntity: RunEntity, rules: List<Rules>, runType: TypeFlow): OUTPUT_DATA

    override fun processFlow(
        inputRequest: InputRequest,
        runEntity: RunEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Any {

        val dataToEvaluate = this.getExternalInfo( inputRequest )
        logger.info("RULE VALIDATOR -- running -- [{}]", runEntity.traceabilityId)

        val responseEvaluation = this.completeEvaluation(inputRequest.input, runEntity, rules, runType)
        logger.info("RULE ENGINE -- running -- evaluation [{}]", responseEvaluation.toJsonString())

        return responseEvaluation as Any
    }
}