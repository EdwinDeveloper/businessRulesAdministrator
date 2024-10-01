package com.rule.evaluator.service.flow

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.RunEntity
import com.rule.evaluator.service.processor.FlowProcessor
import com.rule.evaluator.util.toJsonString
import org.slf4j.LoggerFactory
import java.util.*

abstract class Flow<PROCESS_DATA, DATA, OUTPUT_DATA>: FlowProcessor {

    private val logger = LoggerFactory.getLogger(this::class.java)
    protected abstract fun getInformation(flow: String, inputData: Map<String, Any>, traceabilityId: UUID): PROCESS_DATA
    protected abstract fun completeValuation(requestData: Map<String, Any>, runEntity: RunEntity, rules: List<Rules>, runType: TypeFlow): OUTPUT_DATA

    override fun processFlow(
        inputRequest: InputRequest,
        requestData: Map<String, Any>,
        runEntity: RunEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Any {

        val processData = this.getInformation( runEntity.flow ,requestData, runEntity.traceabilityId ?: UUID.randomUUID())
        logger.info("RULE VALIDATOR -- running -- [{}]", runEntity.traceabilityId)

        val response = this.completeValuation(requestData, runEntity, rules, runType)
        logger.info("RULE ENGINE -- execute -- takeDecision [{}]", response.toJsonString())

        return response as Any
    }
}