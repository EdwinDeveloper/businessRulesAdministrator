package com.rule.evaluator.service

import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.EvaluatorEntity
import com.rule.evaluator.repository.EvaluatorRepository
import com.rule.evaluator.util.toJsonString
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Execution {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var rulesFlowMapper: RulesFlowMapper

    @Autowired
    private lateinit var evaluatorRepository : EvaluatorRepository

    fun start(inputRequest: InputRequest): Any {

        logger.info("RULE-VALIDATOR-SERVICE -- running -- init [{}]", inputRequest.traceabilityId)
        val flow = rulesFlowMapper.getGroup(inputRequest)
        logger.info("RULE-VALIDATOR-SERVICE -- running -- getFlows [{}] [{}]", flow::class.java.simpleName, inputRequest.traceabilityId)

        val evaluatorEntity = EvaluatorEntity(
            input = inputRequest.toJsonString(),
            traceabilityId = inputRequest.traceabilityId,
            userId = inputRequest.user,
            response = null,
            createdAt = LocalDateTime.now(),
            ruleId = null,
            flow = inputRequest.groupId
        )
        evaluatorRepository.save(evaluatorEntity)

        val response = flow.first.processFlow(inputRequest, evaluatorEntity, flow.second.rules, flow.second.runType)
        val responseString = response.toJsonString()
        logger.info("RULE-VALIDATOR-SERVICE -- running -- result [{}]", responseString)
        return response
    }
}