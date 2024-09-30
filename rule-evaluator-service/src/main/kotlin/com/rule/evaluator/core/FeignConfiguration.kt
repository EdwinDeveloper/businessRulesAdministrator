package com.rule.evaluator.core

import com.rule.evaluator.common.exception.GeneralException
import feign.Logger
import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.lang.Exception

@Configuration
class FeignConfiguration {
    @Bean
    fun feignLoggerLevel(): Logger.Level? {
        return Logger.Level.FULL
    }

    @Bean
    fun logger(): Logger {
        return FeignLogger()
    }

    @Bean
    fun errorDecoder(): FeignLogger.FeignErrorDecoder {
        return FeignLogger.FeignErrorDecoder()
    }
}

@Component
class FeignLogger : Logger() {

    val logger: org.slf4j.Logger = LoggerFactory.getLogger(this::class.java)
    override fun log(configKey: String?, format: String?, vararg args: Any?) {
        TODO("Not yet implemented")
    }

    class FeignErrorDecoder : ErrorDecoder {
        override fun decode(methodKey: String?, response: Response): Exception {
            when(response.status()){
                503, 404, 401, 501 -> {
                    val errorMessage = response.body().toString()
                    return GeneralException(
                        statusCode = HttpStatus.valueOf(response.status()),
                        code = response.status().toString(),
                        message = errorMessage.trim()
                    )
                }
                else -> {
                    return GeneralException(
                        statusCode = HttpStatus.valueOf(response.status()),
                        code = response.status().toString(),
                        message = "NOT IDENTIFY ERROR"
                    )
                }
            }
        }

    }
}