package com.rule.evaluator.common.exception

import org.springframework.http.HttpStatus

data class GeneralException (
    val statusCode: HttpStatus,
    val code: String,
    override val message: String,
    override val cause: Throwable? = null,
    val trace_id: String? = null
) : RuntimeException()