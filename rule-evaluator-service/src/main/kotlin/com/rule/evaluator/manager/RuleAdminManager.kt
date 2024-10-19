package com.rule.evaluator.manager

import com.rule.evaluator.client.RuleAdminClient
import com.rule.evaluator.common.enums.TypeFlow
import com.rule.evaluator.common.request.RuleAdminRequest
import com.rule.evaluator.common.response.RuleAdminResponse
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Component

@Component
class RuleAdminManager {

    companion object {
        const val MAX_ATTEMPTS = 3
        const val BACKOFF = 2000L
    }

//    @Retryable(
//        recover = "getEventsRecover",
//        maxAttempts = MAX_ATTEMPTS,
//        backoff = Backoff(BACKOFF),
//        value = [GeneralException::class]
//    )
    fun getRules(ruleStoreRequest: RuleAdminRequest): RuleAdminResponse {
        return RuleAdminResponse(
            ArrayList(),
            "",
            2,
            "",
            TypeFlow.LINEAL
        )
    }

}