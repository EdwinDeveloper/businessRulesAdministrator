package com.rule.evaluator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = ["com.rule.evaluator.entity"])
@EnableJpaRepositories(basePackages = ["com.rule.evaluator.repository"])
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
