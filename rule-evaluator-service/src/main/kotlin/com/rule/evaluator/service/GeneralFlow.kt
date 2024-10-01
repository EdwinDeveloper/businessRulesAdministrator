package com.rule.evaluator.service

import com.rule.evaluator.common.Rules
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.entity.RunEntity
import com.rule.evaluator.service.flow.Flow
import org.springframework.stereotype.Service

@Service
class GeneralFlow : Flow<Map<String, Any>, Any>() {
    override fun getExternalInfo(inputRequest: InputRequest): Map<String, Any> {
        TODO("Not yet implemented")
    }

    override fun completeEvaluation(
        requestData: Map<String, Any>,
        runEntity: RunEntity,
        rules: List<Rules>,
        runType: TypeFlow
    ): Any {
        TODO("Not yet implemented")
    }


}