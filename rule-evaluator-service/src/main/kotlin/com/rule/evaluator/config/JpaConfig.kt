package com.rule.evaluator.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource

@Configuration
class JpaConfig {
    @Bean
    @Primary
    fun entityManagerFactory(
        dataSource: DataSource,
        jpaVendorAdapter: JpaVendorAdapter
    ): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.jpaVendorAdapter = jpaVendorAdapter
        em.setPackagesToScan("com.rule.evaluator.entity")
        return em
    }
}