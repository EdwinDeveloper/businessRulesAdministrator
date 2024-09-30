package com.rule.evaluator.service

import com.rule.evaluator.common.request.InputRequest
import org.springframework.stereotype.Service

@Service
class execution {
    fun start(task: String, inputRequest: InputRequest): Any {
        val flow = 12; //TODO GET THE RULES FROM RULE ADMINISTRATOR
        return 12;
    }
}