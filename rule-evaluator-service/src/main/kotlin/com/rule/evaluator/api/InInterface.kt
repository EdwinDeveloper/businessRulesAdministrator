package com.rule.evaluator.api

import com.rule.evaluator.common.InputRequest
import org.springframework.http.ResponseEntity

interface InInterface {

    fun run(inputRequest: InputRequest): ResponseEntity<*>

}