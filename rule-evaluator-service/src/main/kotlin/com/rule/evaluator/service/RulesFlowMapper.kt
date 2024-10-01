package com.rule.evaluator.service

import com.rule.evaluator.common.response.RuleAdminResponse
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.common.request.RuleAdminRequest
import com.rule.evaluator.manager.RuleAdminManager
import com.rule.evaluator.service.processor.FlowProcessor
import com.rule.evaluator.service.structures.Lineal
import com.rule.evaluator.service.structures.Tree
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RulesFlowMapper {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var ruleStoreManager: RuleAdminManager

    @Autowired
    private lateinit var lineal: Lineal

    @Autowired
    private lateinit var tree: Tree

    fun getRules(
        inputRequest: InputRequest
    ): Pair<FlowProcessor, RuleAdminResponse>{
        val ruleAdminResponse = try {
            ruleStoreManager.getRules(
                RuleAdminRequest(
                    task = inputRequest.task,
                    user = inputRequest.user,
                    traceabilityId = inputRequest.traceabilityId
                )
            )
        } catch (ex: Exception) {
            RuleAdminResponse(rules = ArrayList(), runType = TypeFlow.LINEAL, userId = inputRequest.user)
        }

        val flow = when(ruleAdminResponse.runType){
            TypeFlow.LINEAL -> lineal
            TypeFlow.TREE -> tree
        }

        ruleAdminResponse.rules.sortedBy { it.priority }
        return  Pair(flow, ruleAdminResponse)
    }

}