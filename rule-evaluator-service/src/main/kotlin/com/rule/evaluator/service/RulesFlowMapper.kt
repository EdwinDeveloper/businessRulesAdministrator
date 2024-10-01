package com.rule.evaluator.service

import com.rule.evaluator.common.response.RuleAdminResponse
import com.rule.evaluator.common.Task
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.common.request.RuleAdminRequest
import com.rule.evaluator.manager.RuleAdminManager
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RulesFlowMapper {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var ruleStoreManager: RuleAdminManager

    fun getRules(
        inputRequest: InputRequest
    ): RuleAdminResponse{
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

        ruleAdminResponse.rules.sortedBy { it.priority }
        return ruleAdminResponse
    }

}