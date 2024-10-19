package com.rule.evaluator.service

import com.rule.evaluator.client.WebClientService
import com.rule.evaluator.common.response.RuleAdminResponse
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.common.request.RuleAdminRequest
import com.rule.evaluator.service.flow.Flow
import com.rule.evaluator.service.processor.FlowProcessor
import com.rule.evaluator.service.structures.Lineal
import com.rule.evaluator.service.structures.Tree
import org.apache.tomcat.util.digester.Rule
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class RulesFlowMapper {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var lineal: Lineal

    @Autowired
    private lateinit var tree: Tree

    @Autowired
    private lateinit var webClientService: WebClientService

    fun getGroup(inputRequest: InputRequest): Pair<FlowProcessor, RuleAdminResponse> {
        return try {
            val response = webClientService.getRequest(
                inputRequest.groupId,
                emptyMap(),
                RuleAdminResponse::class.java
            ).block() ?: throw RuntimeException("Response was null")

            val flow: FlowProcessor = when (response.runType) {
                TypeFlow.LINEAL -> lineal
                TypeFlow.TREE -> tree
            }
            val sortedRules = response.rules.sortedBy { it.priority }

            val ruleAdminResponse = RuleAdminResponse(
                id = response.id,
                rules = sortedRules,
                GroupName = response.GroupName,
                runType = response.runType,
                userId = inputRequest.user
            )
            Pair(flow, ruleAdminResponse)

        } catch (ex: Exception) {

            val defaultRuleAdminResponse = RuleAdminResponse(
                id = UUID.randomUUID(),
                rules = ArrayList(),
                GroupName = "NOT AVAILABLE",
                runType = TypeFlow.LINEAL,
                userId = inputRequest.user
            )
            Pair(lineal, defaultRuleAdminResponse)
        }
    }

}