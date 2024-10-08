package com.rule.evaluator.controller

import com.rule.evaluator.api.InInterface
import com.rule.evaluator.common.request.InputRequest
import com.rule.evaluator.service.Execution
import com.rule.evaluator.util.toJsonString
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Executor

@RestController
class InController: InInterface {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var executor: Execution

    @PostMapping("/in", produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun run(@RequestBody inputRequest: InputRequest): ResponseEntity<*> {
        logger.info("Evaluator :: InputRequest: [{}]", inputRequest.toJsonString())
        val response = executor.start(inputRequest)
        return ResponseEntity.ok(response)
    }
}