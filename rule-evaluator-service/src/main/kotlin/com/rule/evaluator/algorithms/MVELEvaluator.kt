package com.rule.evaluator.algorithms

import org.slf4j.LoggerFactory
import java.lang.Exception
import org.mvel2.MVEL
import org.springframework.stereotype.Service

@Service
class MVELEvaluator {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun evaluatorMVel(conditions: String, dataToEvaluate: Any) : Boolean{
        try{
            val evaluation = MVEL.evalToBoolean(conditions.replace("'", ""), dataToEvaluate)
            logger.info("RULE-EVALUATOR-SERVICE : condition : {} ", conditions)
            return evaluation
        }catch (e: Exception){
            logger.error("The evaluation wasn't complete : Condition {} : Error {} ", conditions,  e.message)
        }
        return false
    }

}