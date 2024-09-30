package com.rule.evaluator.client

import com.rule.evaluator.common.request.RuleAdminRequest
import com.rule.evaluator.common.response.RuleAdminResponse
import com.rule.evaluator.core.FeignConfiguration
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "RuleAdmin", url = "\${feign.client.url.ruleadmin}", configuration = [FeignConfiguration::class])
interface RuleAdminClient {

    @PostMapping("/flow")
    fun getEvents(@RequestBody ruleAdminRequest: RuleAdminRequest): RuleAdminResponse
}