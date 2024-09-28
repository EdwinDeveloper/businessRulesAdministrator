package com.rule.evaluator.controller

import com.rule.evaluator.util.route
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [(route.HEALTH)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class controller {

    @GetMapping
    @ResponseBody
    fun getHealthCheck(): ResponseEntity<HashMap<String, String>> = ResponseEntity(hashMapOf("status" to "UP"), HttpStatus.OK)
}