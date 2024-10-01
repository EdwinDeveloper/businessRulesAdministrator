package com.rule.evaluator.service.flow

import com.rule.evaluator.service.processor.FlowProcessor
import org.slf4j.LoggerFactory

abstract class Flow<PROCESS_DATA, DATA, OUTPUT_DATA>: FlowProcessor {

    private val logger = LoggerFactory.getLogger(this::class.java)


}