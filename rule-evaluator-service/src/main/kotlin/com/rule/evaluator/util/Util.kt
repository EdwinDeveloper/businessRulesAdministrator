package com.rule.evaluator.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun Any?.toJsonString(): String {
    return jacksonObjectMapper().writeValueAsString(this)
}