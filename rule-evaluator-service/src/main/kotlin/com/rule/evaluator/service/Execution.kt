package com.rule.evaluator.service

import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.RunEntity
import com.rule.evaluator.repository.RunRepository
import com.rule.evaluator.util.toJsonString
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class Execution {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var rulesFlowMapper: RulesFlowMapper

    @Autowired
    private lateinit var runRepository : RunRepository

    fun start(inputRequest: InputRequest): Any {

        logger.info("RULE-VALIDATOR-SERVICE -- running -- init [{}]", inputRequest.traceabilityId)
        val flow = rulesFlowMapper.getRules(inputRequest)
        logger.info("RULE-VALIDATOR-SERVICE -- running -- getFlows [{}] [{}]", flow::class.java.simpleName, inputRequest.traceabilityId)

        val runEntity = RunEntity(
            input = inputRequest.toJsonString(),
            traceabilityId = inputRequest.traceabilityId,
            userId = UUID.fromString(inputRequest.user),
            response = null,
            createdAt = LocalDateTime.now(),
            ruleId = null,
            flow = inputRequest.task.toString()
        )
        val runSaved = runRepository.save(runEntity)

        val response = 2 //flow.processFlow(inputRequest.task, inputRequest.input, runSaved, flow.second, flow.third.runType)
        val responseString = response.toJsonString()
        logger.info("RULE-VALIDATOR-SERVICE -- running -- result [{}]", responseString)
        return response
    }
}