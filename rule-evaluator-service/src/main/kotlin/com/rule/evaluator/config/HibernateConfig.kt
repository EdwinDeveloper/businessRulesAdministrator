package com.rule.evaluator.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import javax.sql.DataSource

@Configuration
class HibernateConfig {

    @Bean
    fun sessionFactory(dataSource: DataSource): LocalSessionFactoryBean {
        val sessionFactory = LocalSessionFactoryBean()
        sessionFactory.setDataSource(dataSource)
        sessionFactory.setPackagesToScan("com.rule.evaluator.entity")
        return sessionFactory
    }

}