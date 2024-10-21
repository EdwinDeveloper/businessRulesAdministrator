package com.rule.evaluator.service

import com.rule.evaluator.algorithms.MVELEvaluator
import com.rule.evaluator.common.Rules
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import com.rule.evaluator.entity.EvaluatorEntity

@Service
abstract class CoreEngine<INPUT : Any, OUTPUT> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var mvlParser: MVELEvaluator

    fun ruleGroupExecution(listOfRules: List<Rules>, dataToEvaluate: INPUT, evaluatorEntity: EvaluatorEntity): OUTPUT {
        logger.info(
            "RULE EVALUATOR --dataToEvaluate [{}] --EvaluatorEntity [{}]",
            dataToEvaluate,
            evaluatorEntity
        )

        val ruleResults = matchRule(listOfRules, dataToEvaluate, evaluatorEntity.traceabilityId ?: UUID.randomUUID())
        logger.info("RULE EVALUATOR --ruleMatch [{}]", ruleResults)

        return outputResult(dataToEvaluate, ruleResults.first, evaluatorEntity)
    }

    protected fun matchRule(listOfRules: List<Rules>, inputData: INPUT, traceabilityId: UUID): Pair<Rules?, Boolean?> {
        listOfRules.forEach { rule ->
            val conditionCodes = rule.conditions

            if (!mvlParser.evaluatorMVel(conditionCodes, inputData)) {
                logger.info("RULE EVALUATOR -- CHECKING [{}]", rule)
                return Pair(rule, false)
            }
        }
        return Pair(null, true)
    }

    protected abstract fun outputResult(
        input: INPUT,
        rules: Rules?,
        evaluatorEntity: EvaluatorEntity
    ): OUTPUT

}