package com.rule.evaluator.service

import com.rule.evaluator.common.request.InputRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class Execution {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var rulesFlowMapper: RulesFlowMapper

    fun start(task: String, inputRequest: InputRequest): Any {

        logger.info("RULE-VALIDATOR-SERVICE -- running -- init [{}]", inputRequest.traceabilityId)
        val flow = rulesFlowMapper.getRules(inputRequest)
        logger.info("RULE-VALIDATOR-SERVICE -- running -- getFlows [{}] [{}]", flow::class.java.simpleName, inputRequest.traceabilityId)


    }
}